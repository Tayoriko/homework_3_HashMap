package students;

import java.util.*;

public class ScoreDB implements  Examinable {

    private int INITIAL_CAPACITY = 256;
    public Map<String, Score> scoreDB = new HashMap<>(INITIAL_CAPACITY);
    private boolean addCheck = false;

    @Override
    public void addScore(Score score) {
        addCheck = true;
        Score check = getScore(score.getStudent(), score.getSubject());
        if (check != null)
        {
            check.addCounter();
            check.setScore(score.getScore());
        }
        else {
            scoreDB.put(score.getId(), score);
        }
        addCheck = false;
    }

    /*
  // Функция поиска и выборки объекта Score по имени и предмету
    public Score getScore(String name, String subject) {
        return scoreDB.entrySet().stream()
                .filter(entry -> entry.getValue().getName().equals(name) && entry.getValue().getSubject().equals(subject))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null); // Если объект не найден, возвращает null
    }
     */
    @Override
    public Score getScore(Student student, ListSubject subject) {
        Score request = scoreDB.entrySet().stream()
                .filter(score -> score.getValue().getStudent().equals(student) && score.getValue().getSubject().equals(subject))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
        if(request == null && !addCheck)
        {
            throw new RuntimeException("data not found");
        }
        return request;
    }


    @Override
    public double getAverageScoreForSubject(ListSubject subject) {
        Double avgScore = 0.0;
        int recordCnt = 0;
        for (Score score : scoreDB.values())
        {
            if (score.getSubject() == subject && score.getCounter() > 1){
                recordCnt++;
                avgScore += score.getScore();
            }
        }
        if (recordCnt == 0) {
            throw new RuntimeException("no records");
        }
        return avgScore / recordCnt;
    }

    @Override
    public Set<String> multipleSubmissionsStudentNames() {
        Set<String> result = new HashSet<>();
        for (Score score : scoreDB.values()){
            if (score.getCounter() > 1){
                result.add(score.student.toString());
            }
        }
        return result;
    }

    @Override
    public Set<String> lastFiveStudentsWithExcellentMarkOnAnySubject(int amount) {
        TreeMap<Integer, String> map = new TreeMap<>();
        Set<String> lastFive = new HashSet<>(5);
        //собрали всех студентов с оценкой "отлично"
        for (Score score : scoreDB.values()){
            if (score.getScore() == 5){
                map.put(score.getLastDate().abs(), score.student.toString());
            }
        }
        //отсортировали по дате и выбрали 5 последних
        int maxKey = map.lastKey();
        lastFive.add(map.get(maxKey));
        for (int i = 0; i < amount - 1; i++) {
            if (map.lowerKey(maxKey) == null){break;}
            else {
                maxKey = map.lowerKey(maxKey);
                lastFive.add(map.get(maxKey));
            }
        }
        return lastFive;
    }

    @Override
    public Collection<Score> getAllScores() {
        return null;
    }
}
