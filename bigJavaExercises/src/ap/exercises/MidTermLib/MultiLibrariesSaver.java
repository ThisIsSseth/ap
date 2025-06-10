package ap.exercises.MidTermLib;

import ap.exercises.MidTermLib.LM.Saver;

import java.util.ArrayList;
import java.util.List;

class MultiLibrariesSaver {
    Saver libListSaver = new Saver("LibraryList");

    public void save(String libNames) {
        libListSaver.writeInto(libNames);
    }

    public List<String> load() { //??
        List<String> libNameList = new ArrayList<>();
        for (String a : libListSaver.readLinetoList()){
            if (a != null || !a.trim().isEmpty()){
                libNameList.add(a);
            }
        }
        libNameList.removeIf(String::isBlank);
        return libNameList.isEmpty() ? new ArrayList<>() : libNameList;
    }
}