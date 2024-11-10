package org.example;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main4 {

    private static void addToList(List<Integer> list) {
        if (list.get(0) <= 0) {
            list.add(456);
        } else {
            list = new ArrayList<>();
        }
    }

    public static void main(String[] args) {

//        List<String> results = integers.stream()
//                .map(integer -> integer * 500)
//                .map(integer -> CompletableFuture.supplyAsync(()->process(integer), executor))
//                .map(CompletableFuture::join)
//                .collect(Collectors.toList());
    }
}
