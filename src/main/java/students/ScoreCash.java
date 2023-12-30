package students;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ScoreCash {
    private class SubjectRecord {
        private int scoreRecords;
        private int scoreSumm;
        private double scoreAvg;
        private DecimalFormat decimalFormat = new DecimalFormat("#.##");
        private int decLimit = 2;

        public SubjectRecord(int score){
            this.scoreRecords = 1;
            this.scoreSumm = score;
            this.scoreAvg = score;
            decimalFormat.setMaximumFractionDigits(decLimit);
        }

        public void addRecord(int score){
            scoreRecords++;
            scoreSumm += score;
            String format = decimalFormat.format((double) scoreSumm / scoreRecords);
            scoreAvg = Double.parseDouble(format);
        }

        public int getScoreRecords() {
            return scoreRecords;
        }

        public int getScoreSumm() {
            return scoreSumm;
        }

        public double getScoreAvg() {
            return scoreAvg;
        }
    }

    private Map<String, SubjectRecord> cashDB = new HashMap<>();

    public void addScore (Score score){
        SubjectRecord record;
        if (!cashDB.containsKey(score.getSubject().getFullName()))
        {
            record = new SubjectRecord(score.getScore());
            cashDB.put(score.getSubject().getFullName(), record);
        }
        else {
            record = cashDB.get(score.getSubject().getFullName());
            record.addRecord(score.getScore());
        }
    }

    public double getScore (ListSubject subject){
        return cashDB.get(subject.getFullName()).getScoreAvg();
    }
    public double getRecords (ListSubject subject){
        return cashDB.get(subject.getFullName()).getScoreRecords();
    }

    public double getSumm (ListSubject subject){
        return cashDB.get(subject.getFullName()).getScoreSumm();
    }
}
