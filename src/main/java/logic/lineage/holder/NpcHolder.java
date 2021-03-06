package logic.lineage.holder;

import logic.lineage.model.Npc;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class NpcHolder {
    private static final NpcHolder instance = new NpcHolder();
    private HashMap<Integer, Npc> npcStore = new HashMap<>();

    public static NpcHolder getInstance() {
        return instance;
    }

    public void addNpc(Npc npc) {
        npcStore.putIfAbsent(npc.getId(), npc);
    }

    public Npc getNpc(int id) {
        return npcStore.get(id);
    }

    public HashMap<Integer, Npc>  getNpcStore() {
        return npcStore;
    }

    public Npc[] getNpcList(String[] ids) {
        Npc[] npcList = new Npc[ids.length];
        int i = 0;
        for (String data : ids) {
            npcList[i++] = npcStore.get(Integer.parseInt(data));
        }

        return npcList;
    }

    public Npc[] getNpcList(int minId, int maxId) {
        return npcStore.entrySet().stream().filter(entry -> entry.getKey() >= minId && entry.getKey() <= maxId).
                map(Map.Entry::getValue).toArray(Npc[]::new);
    }

    public Npc[] getNpcList() {
        Npc[] data = new Npc[npcStore.values().size()];
        npcStore.values().toArray(data);
        return data;
    }

    public Npc[] getNpcListByName(String[] names) {
        return npcStore.values().stream().filter(n -> Arrays.asList(names).contains(n.getName())).toArray(Npc[]::new);
    }
}
