package liu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Dictionary has the most important role, with many parameters.
 * Be careful each time edit anything here.
 * @author Liu
 */
public class Dictionary {
    private List<Word> words = new ArrayList<>();
    private List<WordPair> wordPairs = new ArrayList<>();
    private List<WordGroup> wordGroups = new ArrayList<>();

    private List<String> qnguFileData = new ArrayList<>();
    private List<String> hnomFileData = new ArrayList<>();

    /**
     * returns words list if it is neccessary.
     * @return
     */
    public ArrayList<Word> getList() {
        return (ArrayList<Word>) words;
    }

    /**
     * load() is for loading builded data from file.
     * @param filename
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean loadWordList(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            words = (List<Word>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("Error in reading builded file");
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Data may dont fit the programme");
            return false;
        }
        System.out.println("Loading builded word list succeeded");
        return true;
    }

    @SuppressWarnings("unchecked")
    public boolean loadWordPairList(String wordpairfilename) {
        try {
            FileInputStream fis = new FileInputStream(wordpairfilename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            wordPairs = (List<WordPair>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("Error in reading builded file");
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Data may dont fit the programme");
            return false;
        }
        System.out.println("Loading builded word pair list succeeded");
        return true;
    }

    @SuppressWarnings("unchecked")
    public boolean loadWordGroupList(String wordgroupfilename) {
        try {
            FileInputStream fis = new FileInputStream(wordgroupfilename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            wordGroups = (List<WordGroup>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("Error in reading builded file");
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Data may dont fit the programme");
            return false;
        }
        System.out.println("Loading builded word group list succeeded");
        return true;
    }


    /**
     * save() is for writing builded data into file.
     * @param filename
     * @return
     */
    public boolean saveWordList(String wordlistfilename) {
        try {
            FileOutputStream fos = new FileOutputStream(wordlistfilename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(words);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error in writing builded data");
            return false;
        }
        System.out.println("Saving builded word list succeeded");
        return true;
    }

    public boolean saveWordPairList(String wordpairlistfilename) {
        try {
            FileOutputStream fos = new FileOutputStream(wordpairlistfilename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(wordPairs);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error in writing builded data");
            return false;
        }
        System.out.println("Saving builded word pair list succeeded");
        return true;
    }

    public boolean saveWordGroupList(String wordgrouplistfilename) {
        try {
            FileOutputStream fos = new FileOutputStream(wordgrouplistfilename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(wordGroups);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error in writing builded data");
            return false;
        }
        System.out.println("Saving builded word group list succeeded");
        return true;
    }

    private boolean readQnguFile(String filename) {

        try {
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fis,  StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            String line;
            while ((line = reader.readLine()) != null) {
                qnguFileData.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error in reading Quoc Ngu file");
            return false;
        }
        System.out.println("Reading Quoc Ngu file succeeded");
        return true;
    }

    private boolean readHnomFile(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            String line;
            while ((line = reader.readLine()) != null) {
                hnomFileData.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error in reading Han Nom file");
            return false;
        }
        System.out.println("Reading Han Nom file succeeded");
        return true;
    }

    /**
     * analyses each word with its own corresponding Han Nom character.
     * @param qngufile
     * @param hnomfile
     * @return
     */
    public boolean analyses(String qngufile, String hnomfile) {
        try {
            readQnguFile(qngufile);
            readHnomFile(hnomfile);

            if (qnguFileData.size() != hnomFileData.size()) {
                System.out.println("The data of two files arent suit?");
                return false;
            }

            for (int i = 0; i < qnguFileData.size(); i++) {
                addLine(qnguFileData.get(i), hnomFileData.get(i)); // adds words into dictionary with their own freq
            }
            System.out.println("Adding words completed");

            for (int i = 0; i < qnguFileData.size(); i++) {
                buildWordRelationships(qnguFileData.get(i), hnomFileData.get(i));
            }
            System.out.println("Building word relationships completed");

            qnguFileData.clear();
            hnomFileData.clear();
            System.out.println("Cleaning unnecessary data completed");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error in analysing duo to be out of bounds");
            // occurs when number of words in 2 strings of pair arent equal
            return false;
        }
        return true;
    }

    /**
     * reads a line turn by turn.
     * @param qnguString
     * @param hnomString
     * @return
     */
    public boolean addLine(String qnguString, String hnomString) {
        String[] qnguWords = getSplitedQnguArray(qnguString);
        String[] hnomWords = getSplitedHnomArray(hnomString);
        int j = 0;

        try {
            int tempID;
            for (int i = 0; i < qnguWords.length; i++) { // adds new words or checks if they existed
                tempID = getWordID(qnguWords[i], hnomWords[i]);

                if (tempID == -1) {
                    words.add(new Word(qnguWords[i], hnomWords[i])); // adds...
                } else {
                    words.get(tempID).incFrequency(); // ... or checks
                }
                j = i;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error in adding line due to being out of bounds");
            System.out.println(qnguString);
            System.out.println(hnomString);
            System.out.println("in i= " + j);
            return false;
        }
        return true;
    }

    /**
     * is used to get position of a "word".
     * @param qngu
     * @param hnom
     * @return
     */
    public int getWordID(String qngu, String hnom) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getQngu().equals(qngu) && words.get(i).getHnom().equals(hnom)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * is used to split a Quoc Ngu sequence into "single" words, and puts them into an array.
     * @param qnguString
     * @return
     */
    public String[] getSplitedQnguArray(String qnguString) {

        String[] splitedQnguArrayFirst = new TextProcessing()
                .toNewWritingWay(new TextProcessing().toLowerCase(qnguString).trim())
                .split("[ 0123456789\\p{Punct}]");

        int countEmptyStrings = 0;
        for (int i = 0; i < splitedQnguArrayFirst.length; i++) {
            if (splitedQnguArrayFirst[i].equals("")) {
                countEmptyStrings++;
            }
        }

        String[] splitedQnguArrayFinal = new String[splitedQnguArrayFirst.length - countEmptyStrings];

        int j = 0;
        for (int i = 0; i < splitedQnguArrayFirst.length; i++) {
            if (!splitedQnguArrayFirst[i].equals("")) {
                splitedQnguArrayFinal[j] = splitedQnguArrayFirst[i];
                j++;
            }
        }

        return splitedQnguArrayFinal;
    }

    /**
     * is used to split a Han Nom sequence into "single" words, and puts them into an array.
     * @param hnomString
     * @return
     */
    public String[] getSplitedHnomArray(String hnomString) {
        List<String> splitedHnomList = new ArrayList<>();
        String qnguexceptions = " " + new NumbersAndPuctuations().getQnguHas(); // space is used to split words too
        String hnomexceptions = new NumbersAndPuctuations().getHnomHas();
        boolean mayBeUtf16ValuePair = false;
        final int startOfFirstUtf16ValueOfPair = 0xd800;
        final int endOfFirstUtf16ValueOfPair = 0xdbff;
        final int startOfSecondUtf16ValueOfPair = 0xdc00;
        final int endOfSecondUtf16ValueOfPair = 0xdfff;
        // range of the first value in a pair is from 0xd800 to 0xdc00, the second one is 0xdbff to 0xdfff

        for (int i = 0; i < hnomString.length(); i++) {
            if (hnomexceptions.contains(hnomString.charAt(i) + "")
                    || qnguexceptions.contains(hnomString.charAt(i) + "")) {
                continue;
            }
            char currentHnomChar = hnomString.charAt(i);
            if (mayBeUtf16ValuePair) {
                if (startOfSecondUtf16ValueOfPair <= currentHnomChar
                        && currentHnomChar <= endOfSecondUtf16ValueOfPair) {
                    String edit = splitedHnomList.get(splitedHnomList.size() - 1) + currentHnomChar;
                    splitedHnomList.remove(splitedHnomList.size() - 1);
                    splitedHnomList.add(edit);
                } else {
                    splitedHnomList.add(currentHnomChar + "");
                }
                mayBeUtf16ValuePair = false;
            } else {
                splitedHnomList.add(currentHnomChar + "");
                if (startOfFirstUtf16ValueOfPair <= currentHnomChar
                        && currentHnomChar <= endOfFirstUtf16ValueOfPair) {
                    mayBeUtf16ValuePair = true; // notifs the next loop there is an appropriate value in the front
                }
            }
        }
        String[] hnomStringArray = new String[splitedHnomList.size()];
        for (int i = 0; i < hnomStringArray.length; i++) {
            hnomStringArray[i] = splitedHnomList.get(i);
        }
        return hnomStringArray;
    }

    /**
     * builds word pairs and word groups that collected from training data.
     * @param qnguString
     * @param hnomString
     * @return
     */
    public boolean buildWordRelationships(String qnguString, String hnomString) {
        int j;

        try {
            String[] qnguWords = new TextProcessing()
                    .toNewWritingWay(new TextProcessing().toLowerCase(qnguString).trim())
                    .split("[0123456789\\p{Punct}]");
            String[] hnomWords = getSplitedHnomArray(hnomString);

            for (int i = 0; i < qnguWords.length; i++) {
                qnguWords[i] = qnguWords[i].trim(); // deletes redundant spaces
            }


            j = 0; // for browsing in array hnomWords (parallel browsing)
            for (int i = 0; i < qnguWords.length; i++) {
                if (qnguWords[i].equals("")) {
                    continue;
                    // due to limitation of method soplit() (maybe), empty and one space strings can be created
                }

                String[] singleWords = qnguWords[i].trim().split(" ");
                // zero character long array can be created
                if (singleWords.length == 0) {
                    continue; // skips these such cases
                }
                if (singleWords.length == 1) { // in other words, it is a single word
                    j++; // j skips the single word
                    continue;

                } else if (singleWords.length == 2) { // here is a pair of word
                    int firstWord = getWordID(singleWords[0], hnomWords[j]);
                    // gets the first word (in the pair) from dictionary
                    j++;
                    int secondWord = getWordID(singleWords[1], hnomWords[j]);
                    // gets the second word (in the pair) from dictionary
                    j++;

                    int tempID = getWordPairID(firstWord, secondWord);

                    if (tempID == -1) { // if the pair doesnt exist yet
                        wordPairs.add(new WordPair(firstWord, secondWord)); // then adds the pair
                    } else {
                        wordPairs.get(tempID).incFrequency(); // increases its frequency instead
                    }
                    words.get(firstWord).decFrequency();
                    words.get(secondWord).decFrequency();
                    // removes cases that arent considered to be part of word pairs

                } else { // a group of word has more than 2 words
                    int[] group = new int[singleWords.length];
                    for (int k = 0; k < singleWords.length; k++) {
                        group[k] = getWordID(singleWords[k], hnomWords[j]);
                        j++;
                    }
                    int tempID = getWordGroupID(group);
                    if (tempID == -1) {
                        wordGroups.add(new WordGroup(group)); // adds the group
                    } else {
                        wordGroups.get(tempID).incFrequency(); // same with the above
                    }
                    for (int k = 0; k < group.length; k++) {
                        words.get(group[k]).decFrequency();
                        // removes cases that arent considered to be part of word groups
                    }
                }

            }
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error in building word relationships due to being out of bounds");
            System.out.println(qnguString);
            System.out.println(hnomString);
            return false;
        }
    }

    /**
     * gets ID of a pair of word in the wordPairs list.
     * @param firstWord
     * @param secondWord
     * @return
     */
    public int getWordPairID(int firstWord, int secondWord) {
        for (int i = 0; i < wordPairs.size(); i++) {
            if (wordPairs.get(i).getFirstWordID() == firstWord
                    && wordPairs.get(i).getSecondWordID() == secondWord) {
                return i;
            }
        }
        return -1;
    }

    /**
     * gets ID of a group of word in the wordGroups list.
     * @param wordGroup
     * @return
     */
    public int getWordGroupID(int[] wordGroup) {
        for (int i = 0; i < wordGroups.size(); i++) {
            if (isEqualIntArray(wordGroups.get(i).getGroup(), wordGroup)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEqualIntArray(int[] array1, int[] array2) {
        if (array1.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * does synthesising processes from original Quoc Ngu texts and returns
     * appropriate Han Nom texts.
     * @param qnguString
     * @return
     */
    public String performance(String qnguString) {
        if ("".equals(qnguString)) { // ignores empty strings
            return "";
        }

        String hnomString; // output
        String[] analysing = new TextProcessing().toNewWritingWay(new TextProcessing().toLowerCase(qnguString).trim())
                .split("[0123456789\\p{Punct}]");

        for (int i = 0; i < analysing.length; i++) {
            analysing[i] = analysing[i].trim(); // deletes redundant spaces
        }

        List<Word> sortedWords = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            sortedWords.add(words.get(i));
        }
        Collections.sort(sortedWords); // priorities words whose frequency is higher

        List<WordPair> sortedWordPairs = new ArrayList<>();
        for (int i = 0; i < wordPairs.size(); i++) {
            sortedWordPairs.add(wordPairs.get(i));
        }
        Collections.sort(sortedWordPairs); // priorities word pairs whose frequency is higher

        List<WordGroup> sortedWordGroups = new ArrayList<>();
        for (int i = 0; i < wordGroups.size(); i++) {
            sortedWordGroups.add(wordGroups.get(i));
        }

        WordPair avoidIgnoringRemaingObjectInGroupList = new WordPair(-1, -1);
        avoidIgnoringRemaingObjectInGroupList.setFrequency(0);
        sortedWordPairs.add(avoidIgnoringRemaingObjectInGroupList);
        // makes sure that pair list has the smallest frequency object to browse all objects in the group list
        // a pair or a group with .getFrequency() == 0 never exists

        Collections.sort(sortedWordGroups); // priorities word groups whose length is longer

        List<String> completedHnomsequences = new ArrayList<>();

        for (int i = 0; i < analysing.length; i++) {

            if (analysing[i].length() == 0) { // skips all empty strings
                continue;
            }

            String qnguSequence = analysing[i];
            int qnguSequenceLength = qnguSequence.split(" ").length;

            boolean[] mark = new boolean[qnguSequenceLength]; // marks which word is already finished

            String[] hnomSequence = new String[qnguSequenceLength];
            // contains Han Nom words in a sequence
            int k = 0;

            for (int j = 0; j < sortedWordPairs.size(); j++) {

                if (sortedWordGroups.size() > 0) { // in some cases, there arent any word group in trained data

                    while (k < sortedWordGroups.size()
                            && sortedWordPairs.get(j).getFrequency() <= sortedWordGroups.get(k).getFrequency()) {
                        // browses pair list and group in the same time (parallel browsing)

                        int[] currentIDSequence = sortedWordGroups.get(k).getGroup();
                        // gets current ID sequence from group list

                        String getQnguFromID = idToQnguOrHnom(currentIDSequence, "qngu");
                        // gets Quoc Ngu sequence from the ID sequence

                        int posInString =
                                getposInString(qnguSequence, getQnguFromID, 0);

                        if (posInString != -1) { // if the sequence doesnt exist in the input, skips it

                            int posInStringPrevTime = -1;
                            // compares to the previous value, if it doesnt change,
                            // it means there are no more changable places in sequence
                            while (posInString != -1 && posInString != posInStringPrevTime
                                    && posInString + currentIDSequence.length <= mark.length) {
                                // scans all sililar cases in the sequence.
                                // when the sequence given from analysed lists no longer have available to be
                                // in the applied sequence (Out of bound)

                                posInStringPrevTime = posInString;
                                boolean check = true;
                                // checks whether the sequence is able to add into the output (hnomSequence[])


                                for (int l = posInString; l < posInString + currentIDSequence.length; l++) {
                                    if (mark[l]) { // if the place is used by prior results
                                        check = false;
                                        break;
                                    }
                                }
                                if (check) {


                                    for (int l = 0; l < sortedWordGroups.get(k).getGroup().length; l++) {
                                        mark[l + posInString] = true; // marks where the result used
                                        hnomSequence[l + posInString] = words.get(currentIDSequence[l]).getHnom();
                                        // adds the result into the sequence for joining into output
                                    }
                                }

                                // tempPos = analysing[i].indexOf(getQnguFromID, tempPos + 1);
                                // skips visited objects in the front
                                posInString = getposInString(qnguSequence, getQnguFromID, posInString + getQnguFromID.length());
                                // applies for all same places in the sequence if they are

                            }
                        }
                        k++; // continues to increase k in order to browse to the next object in the group
                             // list
                    }
                    // if adding an object with frequency == 0 can resolve this part above, there
                    // are no more following parts to continue to browsing the group list

                }

                int currentIDPairFirst = sortedWordPairs.get(j).getFirstWordID(); // gets current ID pair from pair list
                int currentIDPairSecond = sortedWordPairs.get(j).getSecondWordID();

                String getQnguFromID = idToQnguOrHnom(currentIDPairFirst, currentIDPairSecond, "qngu");
                // gets Quoc Ngu sequence from the ID pair
                // int tempPos = analysing[i].indexOf(getQnguFromID); // gets position where the Quoc Ngu sequence is in
                                                                   // the input
                int posInString = getposInString(qnguSequence, getQnguFromID, 0);

                if (posInString != -1) { // if the sequence doesnt exist in the input, skips it

                    int posInStringPrevTime = -1;
                    // compares to the previous value, if it doesnt change, it means there are no more changable places in sequence
                    while (posInString != -1 && posInString != posInStringPrevTime && posInString + 1 < mark.length) {
                        // ends processing when Out Of Bound appears
                        
                        posInStringPrevTime = posInString;


                        if (!mark[posInString] && !mark[posInString + 1]) {
                            // if both consecutive places arent used yet by prior results

                            mark[posInString] = true; // marks where the result used
                            mark[posInString + 1] = true;
                            hnomSequence[posInString] = words.get(currentIDPairFirst).getHnom();
                            // add the result into the output
                            hnomSequence[posInString + 1] = words.get(currentIDPairSecond).getHnom();

                        }
                        // tempPos = analysing[i].indexOf(getQnguFromID, tempPos + 1);
                        // skips visited objects in the front
                        posInString = getposInString(qnguSequence, getQnguFromID, posInString + 2);
                        // +2 means skipping all the word pair
                        // applies for all same places in the sequence if they are

                    }
                }
            }

            boolean checkIfTheOutputFinished = true;
            // to save a little of time after checking all word pairs and word groups
            for (int j = 0; j < mark.length; j++) {
                if (!mark[j]) {
                    checkIfTheOutputFinished = false;
                    break;
                }
            }
            if (!checkIfTheOutputFinished) {

                for (int j = 0; j < sortedWords.size(); j++) { // pay attention that this list is independent from words
                                                               // list
                    String getQnguFromID = sortedWords.get(j).getQngu();
                    // int tempPos = analysing[i].indexOf(getQnguFromID);
                    int posInString = getposInString(qnguSequence, getQnguFromID, 0);

                    if (posInString != -1) {

                        int posInStringPrevTime = -1;
                        // compares to the previous value, if it doesnt change, it means there are no more changable places in sequence
                        while (posInString != -1 && posInString != posInStringPrevTime) {
                            
                            // cause working with groups with one object doesnt make OutOfBound Exception

                            posInStringPrevTime = posInString;
                            if (!mark[posInString]) {
                                mark[posInString] = true;
                                hnomSequence[posInString] = sortedWords.get(j).getHnom();
                            }

                            // tempPos = analysing[i].indexOf(getQnguFromID, tempPos + 1);
                            // skips visited objects in the front
                            posInString = getposInString(qnguSequence, getQnguFromID, posInString + 1);
                            // +1 means skipping the single word
                            // scans all similar cases in the sequence
                        }
                    }
                }

                for (int j = 0; j < hnomSequence.length; j++) {
                    if (hnomSequence[j] == null) { // these are words having not been yet in the data
                        hnomSequence[j] = qnguSequence.split(" ")[j];
                        // gets the Quoc Ngu word in the appropriate position
                    }
                }
            }

            String hnomSequenceString = "";
            for (int j = 0; j < hnomSequence.length; j++) {
                hnomSequenceString += hnomSequence[j];
                // for (int k = 0; k < hnomSequenceString.length(); k++) {
                //     System.out.print((int) hnomSequenceString.charAt(k) + " ");
                    
                // }
                // System.out.println();
            }

            completedHnomsequences.add(hnomSequenceString); // adds completed Han Nom sequences

        }

        List<String> splitedQnguSequences = new ArrayList<>();
        for (int i = 0; i < analysing.length; i++) {
            if (!analysing[i].equals("")) {
                splitedQnguSequences.add(analysing[i]);
            }
        }
        hnomString = new TextProcessing().toNewWritingWay(new TextProcessing().toLowerCase(qnguString)); // applies the “ and ” exception avoidance

        for (int j = 0; j < splitedQnguSequences.size(); j++) {
            int replacementStartPos = hnomString.indexOf(splitedQnguSequences.get(j));
            int ReplacementEndPos = replacementStartPos + splitedQnguSequences.get(j).length();
            
            hnomString = hnomString.substring(0, replacementStartPos)
                        + completedHnomsequences.get(j)
                        + ((ReplacementEndPos >= hnomString.length()) ? "" : hnomString.substring(ReplacementEndPos));
            // applies completed Han Nom sequences into the original string
        }

        hnomString = hnomString.replace(" ", ""); // removes all remaining spaces
        String qnguNumbersAndPuctuations = new NumbersAndPuctuations().getQnguHas();
        // converts Quoc ngu puntuations into Han Nom ones
        for (int j = 0; j < qnguNumbersAndPuctuations.length(); j++) {
            char currentChar = qnguNumbersAndPuctuations.charAt(j);
            hnomString = hnomString.replace(currentChar, new NumbersAndPuctuations().convert(currentChar));
            // be careful with characters encoded by other two characters
        }
        return hnomString;
    }

    public String idToQnguOrHnom(int id, String qnguOrHnom) {
        if (id == -1) {
            return "Something goes wrong?";
        }
        if (qnguOrHnom.equals("qngu"))
            return words.get(id).getQngu();
        if (qnguOrHnom.equals("hnom"))
            return words.get(id).getHnom();
        return "Something goes wrong?";
    }

    public String idToQnguOrHnom(int id1, int id2, String qnguOrHnom) {
        if (id1 == -1 || id2 == -1) {
            return "Here is the end of the list"; // this is the case of the special object above
        }
        if (qnguOrHnom.equals("qngu"))
            return words.get(id1).getQngu() + " " + words.get(id2).getQngu();
        if (qnguOrHnom.equals("hnom"))
            return words.get(id1).getHnom() + words.get(id2).getHnom();
        return "Something goes wrong?";
    }

    public String idToQnguOrHnom(int[] id, String qnguOrHnom) {
        String hnom = "";
        for (int i = 0; i < id.length; i++) {
            if (id[i] == -1) {
                return "Something goes wrong?";
            }
        }
        if (qnguOrHnom.equals("qngu")) {
            for (int i = 0; i < id.length; i++) {
                hnom += words.get(id[i]).getQngu() + " ";
            }
        } else if (qnguOrHnom.equals("hnom")) {
            for (int i = 0; i < id.length; i++) {
                hnom += words.get(id[i]).getHnom();
            }
        }
        return hnom.trim();
    }

    public int getposInString(String consecutiveString, String subString, int fromPosition) {
        if (fromPosition == -1) {
            return -1;
        }
        String splitedWords[] = consecutiveString.trim().split(" ");
        if (fromPosition >= splitedWords.length) {
            return -1;
        }
        String splitedSubStringWords[] = subString.trim().split(" ");
        int startPos = 0, counter = 0;
        boolean comparing = false;
        for (int i = fromPosition; i < splitedWords.length; i++) {
            if (!comparing) {
                startPos = i;
            }
            if (splitedSubStringWords[counter].equals(splitedWords[i])) {
                counter++;
                comparing = true;
                if (counter == splitedSubStringWords.length) {
                    return startPos;
                }
            } else {
                counter = 0;
                comparing = false;
            }
        }
        return -1;
    }

}
