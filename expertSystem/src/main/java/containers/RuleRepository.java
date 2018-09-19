package containers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RuleRepository {

    private List<Question> questions;

    public RuleRepository() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public Iterator<Question> getIterator() {
        return new QuestionIterator();
    }


    private class QuestionIterator implements Iterator<Question> {

        int index;

        @Override
        public boolean hasNext() {
            return index < questions.size();
        }

        @Override
        public Question next() {
            if (this.hasNext())
                return questions.get(index++);

            return null;
        }

        @Override
        public void remove() {
        }
    }

}