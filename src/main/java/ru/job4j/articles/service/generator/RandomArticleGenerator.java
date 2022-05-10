package ru.job4j.articles.service.generator;

import ru.job4j.articles.model.Article;
import ru.job4j.articles.model.Word;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class RandomArticleGenerator implements ArticleGenerator {
    @Override
    public Article generate(List<Word> words) {
        List<SoftReference<Word>> wordsCopy = new ArrayList<>();
        for (Word word: words) {
            wordsCopy.add(new SoftReference<>(word));
        }
        Collections.shuffle(wordsCopy);
        SoftReference<StringJoiner> softJoiner = new SoftReference<StringJoiner>(new StringJoiner(" "));
        for (SoftReference<Word> w : wordsCopy) {
            Word word = w.get();
            StringJoiner joiner = softJoiner.get();
            if (word != null && joiner != null) {
                joiner.add(word.getValue());
            }
        }
        Article article = new Article("");
        StringJoiner joiner = softJoiner.get();
        if (joiner != null) {
            article.setText(joiner.toString());
        }
        return article;
    }
}
