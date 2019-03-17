package com.projectstation.network;

import com.projectstation.network.command.client.ClientWorldVisit;

import java.util.*;

public final class WorldServerHistory implements IImmutableWorldHistory {
    List<IClientVisit> history = new ArrayList<>();
    Map<String, List<IClientVisit>> worldVisits = new HashMap<>();

    private void clear(IClientVisit v) {
        String entityName = getWorldVisitEntityName(v);

        history.remove(v);

        if(worldVisits.containsKey(entityName))
            worldVisits.get(entityName).remove(v);
    }

    private String getWorldVisitEntityName(IClientVisit visit) {
        if(!(visit instanceof ClientWorldVisit))
            return null;

        WorldVisit v = ((ClientWorldVisit)visit).getWorldVisit();
        if(!(v instanceof EntityVisit))
            return null;

        EntityVisit ev = (EntityVisit)v;
        return ev.getEntityName();
    }

    public void eraseEntityHistory(String entityName) {
        if(!worldVisits.containsKey(entityName))
            return;

        List<IClientVisit> erase = worldVisits.get(entityName);
        worldVisits.remove(entityName);

        for(IClientVisit v : erase)
            clear(v);
    }

    public void record(IClientVisit visit) {
        if(!visit.trackHistory())
            return;

        if(history.contains(visit))
            return;

        history.add(visit);

        String entityName = getWorldVisitEntityName(visit);

        if(entityName == null)
            return;

        if(!worldVisits.containsKey(entityName))
            worldVisits.put(entityName, new ArrayList<>());

        List<IClientVisit> workingVisits = worldVisits.get(entityName);

        List<IClientVisit> existingVisits = new ArrayList<>(workingVisits);
        workingVisits.add(visit);

        List<IClientVisit> eliminates = new ArrayList<>();
        IClientVisit mutualEliminateOther = null;

        for(IClientVisit v : existingVisits) {
            if(visit.overrides(v)) {
                eliminates.add(v);
            } else if(visit.mutuallyEliminates(v)) {
                if(eliminates.isEmpty())
                    mutualEliminateOther = v;

                break;
            }
        }

        if(mutualEliminateOther != null) {
            history.remove(mutualEliminateOther);
            history.remove(visit);
            workingVisits.remove(visit);
            workingVisits.remove(mutualEliminateOther);
        } else {
            workingVisits.removeAll(eliminates);
            history.removeAll(eliminates);
        }

    }

    public List<IClientVisit> get()
    {
        return new ArrayList<>(history);
    }

    public int size() {
        return history.size();
    }

}
