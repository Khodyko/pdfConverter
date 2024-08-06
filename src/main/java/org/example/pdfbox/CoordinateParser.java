package org.example.pdfbox;

import java.util.*;

public class CoordinateParser {

    public List<PhraseCoordinate> getPhraseCoordinates(String coordinatedText, String separator) {
        if (separator == null || separator.isEmpty()) {
            throw new RuntimeException("Separator can't be empty");
        }
        List<PhraseCoordinate> result = new ArrayList<>();

        String[] parts = coordinatedText.split(separator);

        for (int i = 1; i < parts.length; i += 2) {
            String coordinateString = parts[i].replace("[", "").replace("]", "");
            String[] coordinates = coordinateString.split(" - ");

            Float latitude = Float.parseFloat(coordinates[0]);
            Float longitude = Float.parseFloat(coordinates[1]);

            String phrase = parts[i + 1];

            PhraseCoordinate pc = new PhraseCoordinate(phrase,  latitude,  longitude);
            result.add(pc);
        }

        return result;

    }

    public Map<Integer, List<PhraseCoordinate>> getStringsOfPhrases(List<PhraseCoordinate> phraseCoordinates){
        float yMaxDiff=4f;
        Map<Integer, List<PhraseCoordinate>> result = new HashMap<>();

        phraseCoordinates.sort(Comparator.comparing(PhraseCoordinate::getyCoordinate));

        float currentY = Float.MIN_VALUE;
        List<PhraseCoordinate> currentList = new ArrayList<>();
        int lineNumber = 0;

        for (PhraseCoordinate pc : phraseCoordinates) {
            if (currentY == Float.MIN_VALUE || Math.abs(pc.getyCoordinate() - currentY) > yMaxDiff) {
                if (!currentList.isEmpty()) {
                    result.put(lineNumber, new ArrayList<>(currentList));
                    currentList.clear();
                    lineNumber++;
                }
                currentY = pc.getyCoordinate();
            }
            currentList.add(pc);
        }

        if (!currentList.isEmpty()) {
            result.put(lineNumber, new ArrayList<>(currentList));
        }

        return result;
    }

    public String getStringsOfPhrases(Map<Integer, List<PhraseCoordinate>> phraseLines){
        StringBuilder stringBuilder = new StringBuilder();
        Float spaceCharLength = 16f;

        for (Map.Entry<Integer, List<PhraseCoordinate>> entry : phraseLines.entrySet()) {
            int key = entry.getKey();
            List<PhraseCoordinate> coordinates = entry.getValue();

            // Sort the list of PhraseCoordinate objects by their x value
            coordinates.sort(Comparator.comparing(PhraseCoordinate::getxCoordinate));

            // Add values in the order of the keys
            for (PhraseCoordinate coordinate : coordinates) {
                stringBuilder.append(coordinate.getPhrase());

                // If the next coordinate's x value is greater than the space character length,
                // add the appropriate number of spaces
                if (coordinates.indexOf(coordinate) < coordinates.size() - 1) {
                    PhraseCoordinate nextCoordinate = coordinates.get(coordinates.indexOf(coordinate) + 1);
                    float distanceBetweenX = nextCoordinate.getxCoordinate() - coordinate.getxCoordinate();
                    int spacesToAdd = Math.max(0, Math.round(distanceBetweenX / spaceCharLength));
                    stringBuilder.append(" ".repeat(spacesToAdd*3));
                }
            }

//             Add a newline character when moving to the next key
            if (key < phraseLines.size()) {
                stringBuilder.append(System.lineSeparator());
            }
        }

        return stringBuilder.toString();
    }
}
