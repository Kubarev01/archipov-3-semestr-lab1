package ru.arkhipov.MyFirstTestAppSpringBoot.hello;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectionsController {
    private ArrayList<String> arrayList;
    private HashMap<Integer, String> hashMap;

    // First call initializes; subsequent calls append, even to an empty collection.
    @GetMapping("/update-array")
    public synchronized String updateArrayList(@RequestParam("s") String s) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            return "ArrayList initialized";
        }
        arrayList.add(s);
        return "ArrayList added: " + s;
    }

    @GetMapping("/show-array")
    public synchronized ArrayList<String> showArrayList() {
        return arrayList == null ? new ArrayList<>() : new ArrayList<>(arrayList);
    }

    @GetMapping("/update-map")
    public synchronized String updateHashMap(@RequestParam("s") String s) {
        if (hashMap == null) {
            hashMap = new HashMap<>();
            return "HashMap initialized";
        }
        hashMap.put(hashMap.size(), s);
        return "HashMap added: " + s;
    }

    @GetMapping("/show-map")
    public synchronized HashMap<Integer, String> showHashMap() {
        return hashMap == null ? new HashMap<>() : new HashMap<>(hashMap);
    }

    // The spelling of the endpoint and method follows the assignment.
    @GetMapping("/show-all-lenght")
    public synchronized String showAllLenght() {
        int arraySize = arrayList == null ? 0 : arrayList.size();
        int mapSize = hashMap == null ? 0 : hashMap.size();
        return "ArrayList: " + arraySize + "; HashMap: " + mapSize;
    }
}
