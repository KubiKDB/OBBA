package com.daniel.obba;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.daniel.obba.database.StatDatabase;
import com.daniel.obba.entities.Statistics;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout let_container;
    TextView[] letters = new TextView[144];
    int[] letter_ids = {
            R.id.let_1,
            R.id.let_2,
            R.id.let_3,
            R.id.let_4,
            R.id.let_5,
            R.id.let_6,
            R.id.let_7,
            R.id.let_8,
            R.id.let_9,
            R.id.let_10,
            R.id.let_0_1,
            R.id.let_0_2,
            R.id.let_11,
            R.id.let_12,
            R.id.let_13,
            R.id.let_14,
            R.id.let_15,
            R.id.let_16,
            R.id.let_17,
            R.id.let_18,
            R.id.let_19,
            R.id.let_20,
            R.id.let_1_1,
            R.id.let_1_2,
            R.id.let_21,
            R.id.let_22,
            R.id.let_23,
            R.id.let_24,
            R.id.let_25,
            R.id.let_26,
            R.id.let_27,
            R.id.let_28,
            R.id.let_29,
            R.id.let_30,
            R.id.let_2_1,
            R.id.let_2_2,
            R.id.let_31,
            R.id.let_32,
            R.id.let_33,
            R.id.let_34,
            R.id.let_35,
            R.id.let_36,
            R.id.let_37,
            R.id.let_38,
            R.id.let_39,
            R.id.let_40,
            R.id.let_3_1,
            R.id.let_3_2,
            R.id.let_41,
            R.id.let_42,
            R.id.let_43,
            R.id.let_44,
            R.id.let_45,
            R.id.let_46,
            R.id.let_47,
            R.id.let_48,
            R.id.let_49,
            R.id.let_50,
            R.id.let_4_1,
            R.id.let_4_2,
            R.id.let_51,
            R.id.let_52,
            R.id.let_53,
            R.id.let_54,
            R.id.let_55,
            R.id.let_56,
            R.id.let_57,
            R.id.let_58,
            R.id.let_59,
            R.id.let_60,
            R.id.let_5_1,
            R.id.let_5_2,
            R.id.let_61,
            R.id.let_62,
            R.id.let_63,
            R.id.let_64,
            R.id.let_65,
            R.id.let_66,
            R.id.let_67,
            R.id.let_68,
            R.id.let_69,
            R.id.let_70,
            R.id.let_6_1,
            R.id.let_6_2,
            R.id.let_71,
            R.id.let_72,
            R.id.let_73,
            R.id.let_74,
            R.id.let_75,
            R.id.let_76,
            R.id.let_77,
            R.id.let_78,
            R.id.let_79,
            R.id.let_80,
            R.id.let_7_1,
            R.id.let_7_2,
            R.id.let_81,
            R.id.let_82,
            R.id.let_83,
            R.id.let_84,
            R.id.let_85,
            R.id.let_86,
            R.id.let_87,
            R.id.let_88,
            R.id.let_89,
            R.id.let_90,
            R.id.let_8_1,
            R.id.let_8_2,
            R.id.let_91,
            R.id.let_92,
            R.id.let_93,
            R.id.let_94,
            R.id.let_95,
            R.id.let_96,
            R.id.let_97,
            R.id.let_98,
            R.id.let_99,
            R.id.let_100,
            R.id.let_9_1,
            R.id.let_9_2,
            R.id.let_101,
            R.id.let_102,
            R.id.let_103,
            R.id.let_104,
            R.id.let_105,
            R.id.let_106,
            R.id.let_107,
            R.id.let_108,
            R.id.let_109,
            R.id.let_110,
            R.id.let_10_1,
            R.id.let_10_2,
            R.id.let_111,
            R.id.let_112,
            R.id.let_113,
            R.id.let_114,
            R.id.let_115,
            R.id.let_116,
            R.id.let_117,
            R.id.let_118,
            R.id.let_119,
            R.id.let_120,
            R.id.let_11_1,
            R.id.let_11_2
    };

    boolean[] isCancel = new boolean[144];

    TextView[] submit_word = new TextView[7];
    int[] s_ids = {
            R.id.submit_1,
            R.id.submit_2,
            R.id.submit_3,
            R.id.submit_4,
            R.id.submit_5,
            R.id.submit_6,
            R.id.submit_7,
    };

    char[] consonants = {
            'ф', 'х', 'ц', 'ч', 'ш',
            'щ', 'ь', 'ю', 'я', 'є',
            'ж', 'ї', 'з', 'г', 'й',

            'б', 'в', 'д', 'к', 'л', 'м', 'н', 'п', 'р', 'с', 'у',
            'б', 'в', 'д', 'к', 'л', 'м', 'н', 'п', 'р', 'с', 'у',
            'б', 'в', 'д', 'к', 'л', 'м', 'н', 'п', 'р', 'с', 'у',
            'б', 'в', 'д', 'к', 'л', 'м', 'н', 'п', 'р', 'с', 'у',

            'и', 'т',
            'и', 'т',
            'и', 'т',
            'и', 'т',
            'и', 'т',
            'и', 'т',

            'а', 'і', 'о', 'е',
            'а', 'і', 'о', 'е',
            'а', 'і', 'о', 'е',
            'а', 'і', 'о', 'е',
            'а', 'і', 'о', 'е',
            'а', 'і', 'о', 'е',
            'а', 'і', 'о', 'е',
            'а', 'і', 'о', 'е'};

    String[] letter_set = new String[144];
    Statistics stats;
    Button test_btn;

    boolean is_new = false;
    String[] storage = new String[365];

    TextView submit_btn, submit_counter;

    int current_letter = 0;

    int[] order = new int[7];

    TextView word_view;

    TextView statistics,
            games_played,
            words_found,
            average_words,
            max_words,
            min_words,
            rules,
            review_btn,
            buy_btn,
            find_word,
            update_btn;
    ConstraintLayout stat_container, rules_container, warning;
    TextView today, today_words_found, today_3, today_4, today_5, today_6, today_7;
    TextView stats_3, stats_4, stats_5, stats_6, stats_7;

    ImageView theme_btn;


    private FirebaseAnalytics mFirebaseAnalytics;
//    private InterstitialAd mInterstitialAd;
    int currentGame = 0;

    TextView share_btn;

//    ReviewInfo reviewInfo;
//    ReviewManager manager;

    boolean isNight = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        isNight = sharedPreferences.getBoolean("isNightTheme", true);


        setTheme(isNight ? R.style.NightActivity : R.style.DayActivity);
        setContentView(R.layout.activity_main);
        theme_btn = findViewById(R.id.theme);
        theme_btn.setBackground(isNight ? getDrawable(R.drawable.white_box_24) : getDrawable(R.drawable.black_box_24));

        let_container = findViewById(R.id.let_container);
        share_btn = findViewById(R.id.share_btn);
        test_btn = findViewById(R.id.test_btn);
        submit_btn = findViewById(R.id.submit_btn);
        update_btn = findViewById(R.id.update_btn);
        warning = findViewById(R.id.warning);
        word_view = findViewById(R.id.word_recycler);
        submit_counter = findViewById(R.id.submit_counter);
        statistics = findViewById(R.id.statistics);
        today = findViewById(R.id.today);
        today_words_found = findViewById(R.id.today_num1);
        today_3 = findViewById(R.id.today_num2);
        today_4 = findViewById(R.id.today_num3);
        today_5 = findViewById(R.id.today_num4);
        today_6 = findViewById(R.id.today_num5);
        today_7 = findViewById(R.id.today_num6);
        stats_3 = findViewById(R.id.stats_num6);
        stats_4 = findViewById(R.id.stats_num7);
        stats_5 = findViewById(R.id.stats_num8);
        stats_6 = findViewById(R.id.stats_num9);
        stats_7 = findViewById(R.id.stats_num10);
        stat_container = findViewById(R.id.stat_container);
        rules_container = findViewById(R.id.rules_container);
        games_played = findViewById(R.id.stats_num1);
        words_found = findViewById(R.id.stats_num2);
        average_words = findViewById(R.id.stats_num3);
        buy_btn = findViewById(R.id.buy_btn);
        find_word = findViewById(R.id.findword_btn);
        max_words = findViewById(R.id.stats_num4);
        min_words = findViewById(R.id.stats_num5);
        review_btn = findViewById(R.id.review_btn);
        rules = findViewById(R.id.rules);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        word_view.setMovementMethod(new ScrollingMovementMethod());

        Date date = new Date();
        final SimpleDateFormat dtf = new SimpleDateFormat("MMM dd yyyy HH:mm", Locale.ENGLISH);
        Date baseTime = new Date();
        try {
            baseTime = dtf.parse("Apr 23 2023 00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        float fl = (date.getTime() - baseTime.getTime());
        currentGame = (int) (fl / 86400000);

//        MobileAds.initialize(this, initializationStatus -> {
//        });

        Arrays.fill(isCancel, false);

        for (int i = 0; i < letters.length; i++) {
            letters[i] = findViewById(letter_ids[i]);
            letters[i].setEnabled(true);
            letters[i].setTextColor(isNight ? getColor(R.color.white) : getColor(R.color.black));
        }

        for (int i = 0; i < submit_word.length; i++) {
            submit_word[i] = findViewById(s_ids[i]);
        }

        statistics.setOnClickListener(view -> {
            stat_container.setVisibility(View.VISIBLE);
        });

        stat_container.setOnClickListener(view -> {
            stat_container.setVisibility(View.GONE);
        });

        rules.setOnClickListener(view -> {
            rules_container.setVisibility(View.VISIBLE);
        });

        rules_container.setOnClickListener(view -> {
            rules_container.setVisibility(View.GONE);
        });

        review_btn.setOnClickListener(view -> {
            review();
        });

        buy_btn.setOnClickListener(view -> {
            String url = "https://play.google.com/store/apps/details?id=com.daniel.slovva";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });

        find_word.setOnClickListener(view -> {
            String url = "https://play.google.com/store/apps/details?id=com.daniel.findword";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });

        share_btn.setOnClickListener(v -> {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String body = "https://play.google.com/store/apps/details?id=com.daniel.obba";
            myIntent.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(myIntent, "Овва"));
        });

        theme_btn.setOnClickListener(v -> {
            isNight = !isNight;
            if (isNight){
                Toast.makeText(this, "Темна тема", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Світла тема", Toast.LENGTH_SHORT).show();
            }
            SharedPreferences.Editor sharedPreferencesEditor =
                    PreferenceManager.getDefaultSharedPreferences(this).edit();
            sharedPreferencesEditor.putBoolean(
                    "isNightTheme", isNight);
            sharedPreferencesEditor.apply();
            recreate();
//            setTheme(isNight ? R.style.NightActivity : R.style.DayActivity);
//            setContentView(R.layout.activity_main);
        });

        @SuppressLint("StaticFieldLeak")
        class GetTask extends AsyncTask<Void, Void, List<Statistics>> {
            @Override
            protected List<Statistics> doInBackground(Void... voids) {
                return StatDatabase.getDatabase(getApplicationContext()).statDao().getAll();
            }

            @Override
            protected void onPostExecute(List<Statistics> stats_arr) {
                super.onPostExecute(stats_arr);
                if (!stats_arr.isEmpty()) {
                    stats = stats_arr.get(0);
                    is_new = false;
                } else {
                    stats = new Statistics();
                    is_new = true;
                }
                if (stats.getGames_played() != currentGame) {
                    stats.setGames_played(currentGame);
                    stats.setWord_string("");
                    stats.setWords_number(0);
                    stats.setPlayed_today(false);

                    stats.setToday_words_number(0);
                    stats.setToday_3(0);
                    stats.setToday_4(0);
                    stats.setToday_5(0);
                    stats.setToday_6(0);
                    stats.setToday_7(0);

                    if (Integer.parseInt(String.valueOf(submit_counter.getText())) != 0) {
                        if (stats.getInt_string() == null) {
                            stats.setInt_string(String.valueOf(Integer.parseInt(String.valueOf(submit_counter.getText()))));
                        } else {
                            stats.setInt_string(stats.getInt_string() + " " + Integer.parseInt(String.valueOf(submit_counter.getText())));
                        }
                    }
                }

                Date date = new Date();
                final SimpleDateFormat dtf = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);

                today_words_found.setText(stats.getToday_words_number() + "");

                today_3.setText(stats.getToday_3() + "");
                today_4.setText(stats.getToday_4() + "");
                today_5.setText(stats.getToday_5() + "");
                today_6.setText(stats.getToday_6() + "");
                today_7.setText(stats.getToday_7() + "");

                stats_3.setText(stats.getStats_3() + "");
                stats_4.setText(stats.getStats_4() + "");
                stats_5.setText(stats.getStats_5() + "");
                stats_6.setText(stats.getStats_6() + "");
                stats_7.setText(stats.getStats_7() + "");

                today.setText(monthsTranslate(dtf.format(date)));
                test_btn.setText(stats.getGames_played() + "");

                try {
                    for (int i = 0; i < letters.length; i++) {
                        letters[i].setText(String.valueOf(LetterStorage.letterStorage[stats.getGames_played()].charAt(i)));
                    }
                } catch (Exception e){
                    for (int i = 0; i < letters.length; i++) {
                        letters[i].setText(String.valueOf(LetterStorage.letterStorage[LetterStorage.letterStorage.length - 1].charAt(i)));
                    }
                    warning.setVisibility(View.VISIBLE);
                    warning.setOnClickListener(v -> {});
                    update_btn.setOnClickListener(v -> {
                        String url = "https://play.google.com/store/apps/details?id=com.daniel.obba";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    });
                }

                word_view.setText(stats.getWord_string());
                int i = stats.getWords_number();
                if (i >= 0 && i <= 9) {
                    submit_counter.setText("00" + i);
                } else if (i >= 10 && i <= 99) {
                    submit_counter.setText("0" + i);
                } else {
                    submit_counter.setText(i + "");
                }

                int si = 0;
                int min = 1000;
                int max = 0;
                if (stats.getInt_string() != null) {
                    String[] split = stats.getInt_string().split(" ");
                    for (int j = 0; j < split.length; j++) {
                        si += Integer.parseInt(split[j]);
                    }
                    for (int j = 0; j < split.length; j++) {
                        if (Integer.parseInt(split[j]) < min) {
                            min = Integer.parseInt(split[j]);
                        }
                        if (Integer.parseInt(split[j]) > max) {
                            max = Integer.parseInt(split[j]);
                        }
                    }
                    games_played.setText(split.length + "");
                    words_found.setText(si + "");
                    average_words.setText(Math.round(si / split.length) + "");
                    min_words.setText(min + "");
                    max_words.setText(max + "");
                }
            }
        }
        new GetTask().execute();

        test_btn.setOnClickListener(view -> {
            stats.setGames_played(stats.getGames_played() + 1);
            stats.setWord_string("");
            stats.setWords_number(0);

            stats.setToday_words_number(0);
            stats.setToday_3(0);
            stats.setToday_4(0);
            stats.setToday_5(0);
            stats.setToday_6(0);
            stats.setToday_7(0);
            stats.setPlayed_today(false);
            if (Integer.parseInt(String.valueOf(submit_counter.getText())) != 0) {
                if (stats.getInt_string() == null) {
                    stats.setInt_string(String.valueOf(Integer.parseInt(String.valueOf(submit_counter.getText()))));
                } else {
                    stats.setInt_string(stats.getInt_string() + " " + Integer.parseInt(String.valueOf(submit_counter.getText())));
                }
            }
            @SuppressLint("StaticFieldLeak")
            class NextTask extends AsyncTask<Void, Void, Void> {
                @Override
                protected Void doInBackground(Void... voids) {
                    if (is_new) {
                        StatDatabase.getDatabase(getApplicationContext()).statDao().insertStat(stats);
                    } else {
                        StatDatabase.getDatabase(getApplicationContext()).statDao().changeStat(stats);
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void unused) {
                    super.onPostExecute(unused);
                    recreate();
                }
            }
            new NextTask().execute();
        });

        submit_btn.setOnClickListener(view -> {
            if (current_letter > 2) {
                String word = "";
                for (int i = 0; i < submit_word.length; i++) {
                    word += submit_word[i].getText().toString();
                }
                boolean doesExist = false;
                String[] split_str = word_view.getText().toString().split(" ");
                for (int i = 0; i < split_str.length; i++) {
                    if (split_str[i].equalsIgnoreCase(word)) {
                        doesExist = true;
                    }
                }

                if (checkWord(word)) {
                    String result = word + " " + word_view.getText();
                    for (int i = 0; i < letters.length; i++) {
                        letters[i].setEnabled(true);
                        letters[i].setTextColor(isNight ? getColor(R.color.white) : getColor(R.color.black));
                    }
                    for (int i = 0; i < submit_word.length; i++) {
                        submit_word[i].setText("");
                    }
                    current_letter = 0;
                    Arrays.fill(isCancel, false);
                    Arrays.fill(order, 0);
                    if (!doesExist) {
                        word_view.setText(result.trim());
                        int i = Integer.parseInt(String.valueOf(submit_counter.getText())) + 1;
                        if (i >= 0 && i <= 9) {
                            submit_counter.setText("00" + i);
                        } else if (i >= 10 && i <= 99) {
                            submit_counter.setText("0" + i);
                        } else {
                            submit_counter.setText(i + "");
                        }
                        stats.setWord_string(word_view.getText().toString().trim());
                        stats.setWords_number(Integer.parseInt(String.valueOf(submit_counter.getText())));

                        Bundle bundle = new Bundle();
                        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, stats.getGames_played() + ":" + stats.getWords_number());
                        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Word");
                        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "text");
                        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


                        if (stats.getInt_string() != null) {
                            String[] split = stats.getInt_string().split(" ");
                            if (stats.isPlayed_today()) {
                                split[Integer.parseInt(String.valueOf(games_played.getText())) - 1] = String.valueOf(Integer.parseInt(submit_counter.getText().toString()));
                            } else {
                                stats.setInt_string(stats.getInt_string() + " " + Integer.parseInt(String.valueOf(submit_counter.getText())));
                                split = stats.getInt_string().split(" ");
                                stats.setPlayed_today(true);
                            }

                            String str = "";
                            for (int j = 0; j < split.length; j++) {
                                str += split[j] + " ";
                            }
                            stats.setInt_string(str.trim());
                        } else {
                            stats.setInt_string(String.valueOf(Integer.parseInt(String.valueOf(submit_counter.getText()))));
                            stats.setPlayed_today(true);
                        }


                        stats.setToday_words_number(stats.getToday_words_number() + 1);

                        switch (word.length()) {
                            case 3:
                                stats.setStats_3(stats.getStats_3() + 1);
                                stats.setToday_3(stats.getToday_3() + 1);
                                break;
                            case 4:
                                stats.setStats_4(stats.getStats_4() + 1);
                                stats.setToday_4(stats.getToday_4() + 1);
                                break;
                            case 5:
                                stats.setStats_5(stats.getStats_5() + 1);
                                stats.setToday_5(stats.getToday_5() + 1);
                                break;
                            case 6:
                                stats.setStats_6(stats.getStats_6() + 1);
                                stats.setToday_6(stats.getToday_6() + 1);
                                break;
                            case 7:
                                stats.setStats_7(stats.getStats_7() + 1);
                                stats.setToday_7(stats.getToday_7() + 1);
                                break;
                        }

                        int si = 0;
                        int min = 1000;
                        int max = 0;
                        if (stats.getInt_string() != null) {
                            String[] split = stats.getInt_string().split(" ");
                            for (int j = 0; j < split.length; j++) {
                                si += Integer.parseInt(split[j]);
                            }
                            for (int j = 0; j < split.length; j++) {
                                if (Integer.parseInt(split[j]) < min) {
                                    min = Integer.parseInt(split[j]);
                                }
                                if (Integer.parseInt(split[j]) > max) {
                                    max = Integer.parseInt(split[j]);
                                }
                            }
                            games_played.setText(split.length + "");
                            words_found.setText(si + "");

                            average_words.setText(Math.round(si / split.length) + "");
                            min_words.setText(min + "");
                            max_words.setText(max + "");
                            today_words_found.setText(stats.getToday_words_number() + "");

                            today_3.setText(stats.getToday_3() + "");
                            today_4.setText(stats.getToday_4() + "");
                            today_5.setText(stats.getToday_5() + "");
                            today_6.setText(stats.getToday_6() + "");
                            today_7.setText(stats.getToday_7() + "");

                            stats_3.setText(stats.getStats_3() + "");
                            stats_4.setText(stats.getStats_4() + "");
                            stats_5.setText(stats.getStats_5() + "");
                            stats_6.setText(stats.getStats_6() + "");
                            stats_7.setText(stats.getStats_7() + "");
                        }

                        @SuppressLint("StaticFieldLeak")
                        class NextTask extends AsyncTask<Void, Void, Void> {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                if (is_new) {
                                    StatDatabase.getDatabase(getApplicationContext()).statDao().insertStat(stats);
                                } else {
                                    StatDatabase.getDatabase(getApplicationContext()).statDao().changeStat(stats);
                                }
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void unused) {
                                super.onPostExecute(unused);
//                                if (stats.getWords_number() % 10 == 0) {
//
//                                    AdRequest adRequest = new AdRequest.Builder().build();
//
//                                    InterstitialAd.load(MainActivity.this, "ca-app-pub-3801792611280684/7109058935", adRequest,
//                                            new InterstitialAdLoadCallback() {
//                                                @Override
//                                                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
//                                                    // The mInterstitialAd reference will be null until
//                                                    // an ad is loaded.
//                                                    mInterstitialAd = interstitialAd;
//                                                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
//                                                        @Override
//                                                        public void onAdClicked() {
//                                                            // Called when a click is recorded for an ad.
//                                                            Log.d(TAG, "Ad was clicked.");
//                                                        }
//
//                                                        @Override
//                                                        public void onAdDismissedFullScreenContent() {
//                                                            // Called when ad is dismissed.
//                                                            // Set the ad reference to null so you don't show the ad a second time.
//                                                            Log.d(TAG, "Ad dismissed fullscreen content.");
//                                                            mInterstitialAd = null;
//                                                        }
//
//                                                        @Override
//                                                        public void onAdFailedToShowFullScreenContent(AdError adError) {
//                                                            // Called when ad fails to show.
//                                                            Log.e(TAG, "Ad failed to show fullscreen content.");
//                                                            mInterstitialAd = null;
//                                                        }
//
//                                                        @Override
//                                                        public void onAdImpression() {
//                                                            // Called when an impression is recorded for an ad.
//                                                            Log.d(TAG, "Ad recorded an impression.");
//                                                        }
//
//                                                        @Override
//                                                        public void onAdShowedFullScreenContent() {
//                                                            // Called when ad is shown.
//                                                            Log.d(TAG, "Ad showed fullscreen content.");
//                                                        }
//                                                    });
//                                                    if (mInterstitialAd != null) {
//                                                        mInterstitialAd.show(MainActivity.this);
//                                                    } else {
//                                                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
//                                                    }
//                                                    Log.i(TAG, "onAdLoaded");
//                                                }
//
//                                                @Override
//                                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                                                    // Handle the error
//                                                    Log.d(TAG, loadAdError.toString());
//                                                    mInterstitialAd = null;
//                                                }
//                                            });
//                                }
                            }
                        }
                        new NextTask().execute();
                    } else {
                        Toast.makeText(this, "Слово вже знайдене", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    for (int i = 0; i < letters.length; i++) {
                        letters[i].setEnabled(true);
                        letters[i].setTextColor(isNight ? getColor(R.color.white) : getColor(R.color.black));
                    }
                    for (int i = 0; i < submit_word.length; i++) {
                        submit_word[i].setText("");
                    }
                    current_letter = 0;
                    Arrays.fill(isCancel, false);
                    Arrays.fill(order, 0);
                    Toast.makeText(this, "Слова немає в списку", Toast.LENGTH_SHORT).show();
                }

            }
        });

        for (int i = 0; i < letters.length; i++) {
            int finalI = i;
            letters[i].setOnClickListener(view -> {
                if (isCancel[finalI]) {
                    if (current_letter >= 2) {
                        for (int j = 0; j < letters.length; j++) {
                            if (letters[j].getCurrentTextColor() != getColor(R.color.blue)) {
                                letters[j].setTextColor(isNight ? getColor(R.color.white) : getColor(R.color.black));
                            }
                        }
                        letters[finalI].setTextColor(isNight ? getColor(R.color.white) : getColor(R.color.black));

                        int order_int = current_letter - 2;

                        current_letter -= 2;

                        letters[order[order_int]].setEnabled(true);
                        letters[order[order_int]].performClick();

                        submit_word[current_letter].setText("");
                    } else {
                        for (int j = 0; j < letters.length; j++) {
                            letters[j].setEnabled(true);
                            letters[j].setTextColor(isNight ? getColor(R.color.white) : getColor(R.color.black));
                        }
                        for (int j = 0; j < submit_word.length; j++) {
                            submit_word[j].setText("");
                        }
                        current_letter = 0;
                        Arrays.fill(isCancel, false);
                        Arrays.fill(order, 0);
                    }
                    return;
                }
                if (current_letter < 7) {
                    for (int j = 0; j < letters.length; j++) {
                        letters[j].setEnabled(false);
                        if (letters[j].getCurrentTextColor() == getColor(R.color.green) || letters[j].getCurrentTextColor() == getColor(R.color.dark_green)) {
                            letters[j].setTextColor(isNight ? getColor(R.color.white) : getColor(R.color.black));
                        }
                    }
                    letters[finalI].setTextColor(getColor(R.color.blue));
                    if (current_letter < 6) {
                        if (finalI % 12 == 11) {
                            if (finalI == 11) {
                                try {
                                    if (letters[finalI - 1].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 1].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI - 1].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 11].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 11].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI + 11].setEnabled(true);
                                    }

                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 12].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI + 12].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                            } else if (finalI == 143) {
                                try {
                                    if (letters[finalI - 13].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 13].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI - 13].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI - 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 12].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI - 12].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI - 1].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 1].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI - 1].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                            } else {
                                try {
                                    if (letters[finalI - 13].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 13].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI - 13].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI - 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 12].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI - 12].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI - 1].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 1].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI - 1].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 11].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 11].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI + 11].setEnabled(true);
                                    }

                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 12].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI + 12].setEnabled(true);
                                    }

                                } catch (Exception ignored) {
                                }
                            }
                        } else if (finalI % 12 == 0) {
                            if (finalI == 0) {
                                try {
                                    if (letters[finalI + 1].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 1].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI + 1].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 12].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI + 12].setEnabled(true);
                                    }

                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 13].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 13].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI + 13].setEnabled(true);
                                    }

                                } catch (Exception ignored) {
                                }
                            } else if (finalI == 132) {
                                try {
                                    if (letters[finalI - 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 12].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI - 12].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI - 11].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 11].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI - 11].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 1].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 1].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI + 1].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                            } else {
                                try {
                                    if (letters[finalI - 11].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 11].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI - 11].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI - 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI - 12].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI - 12].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 1].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 1].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI + 1].setEnabled(true);
                                    }
                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 12].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI + 12].setEnabled(true);
                                    }

                                } catch (Exception ignored) {
                                }
                                try {
                                    if (letters[finalI + 13].getCurrentTextColor() != getColor(R.color.blue)) {
                                        letters[finalI + 13].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                        letters[finalI + 13].setEnabled(true);
                                    }

                                } catch (Exception ignored) {
                                }
                            }
                        } else {
                            try {
                                if (letters[finalI - 13].getCurrentTextColor() != getColor(R.color.blue)) {
                                    letters[finalI - 13].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                    letters[finalI - 13].setEnabled(true);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                    letters[finalI - 12].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                    letters[finalI - 12].setEnabled(true);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 11].getCurrentTextColor() != getColor(R.color.blue)) {
                                    letters[finalI - 11].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                    letters[finalI - 11].setEnabled(true);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 1].getCurrentTextColor() != getColor(R.color.blue)) {
                                    letters[finalI - 1].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                    letters[finalI - 1].setEnabled(true);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 1].getCurrentTextColor() != getColor(R.color.blue)) {
                                    letters[finalI + 1].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                    letters[finalI + 1].setEnabled(true);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 11].getCurrentTextColor() != getColor(R.color.blue)) {
                                    letters[finalI + 11].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                    letters[finalI + 11].setEnabled(true);
                                }

                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 12].getCurrentTextColor() != getColor(R.color.blue)) {
                                    letters[finalI + 12].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                    letters[finalI + 12].setEnabled(true);
                                }

                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 13].getCurrentTextColor() != getColor(R.color.blue)) {
                                    letters[finalI + 13].setTextColor(isNight ? getColor(R.color.green) : getColor(R.color.dark_green));
                                    letters[finalI + 13].setEnabled(true);
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    }

                    submit_word[current_letter].setText(letters[finalI].getText().toString());
                    order[current_letter] = finalI;
                    current_letter++;
                    letters[finalI].setEnabled(true);
                    for (int j = 0; j < isCancel.length; j++) {
                        isCancel[j] = false;
                    }
                    isCancel[finalI] = true;
                }
            });
        }

//        for (int i = 0; i < 365; i++) {
//            storage[i] = "";
//            for (int j = 0; j < letter_set.length; j++) {
//                char[] chars = new char[10];
//                Random r = new Random();
//                for (int k = 0; k < chars.length; k++) {
////                    if (k < 6){
//                        chars[k] = consonants[r.nextInt(consonants.length)];
////                    } else {
////                        chars[k] = vowels[r.nextInt(vowels.length)];
////                    }
//                }
//                String str = String.valueOf(chars[r.nextInt(chars.length)]);
//                letter_set[j] = str;
//                storage[i] += str;
//            }
//        }
//        for (int i = 0; i < letters.length; i++) {
//            letters[i].setText(letter_set[i]);
//        }
    }

    private boolean checkWord(String currWord) {
        boolean does_exist = false;
        switch (currWord.length()) {
            case 3:
                for (int i = 0; i < UkrVoc.three_vocab.length; i++) {
                    if (UkrVoc.three_vocab[i].equalsIgnoreCase(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                break;

            case 4:
                for (int i = 0; i < UkrVoc.four_vocab.length; i++) {
                    if (UkrVoc.four_vocab[i].equalsIgnoreCase(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                break;

            case 5:
                for (int i = 0; i < UkrVoc.five_vocab_p1.length; i++) {
                    if (UkrVoc.five_vocab_p1[i].equalsIgnoreCase(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                for (int i = 0; i < UkrVoc.five_vocab_p2.length; i++) {
                    if (UkrVoc.five_vocab_p2[i].equalsIgnoreCase(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                break;
            case 6:
                for (int i = 0; i < UkrVoc.six_vocab_p1.length; i++) {
                    if (UkrVoc.six_vocab_p1[i].equalsIgnoreCase(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                for (int i = 0; i < UkrVoc.six_vocab_p2.length; i++) {
                    if (UkrVoc.six_vocab_p2[i].equalsIgnoreCase(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                for (int i = 0; i < UkrVoc.six_vocab_p3.length; i++) {
                    if (UkrVoc.six_vocab_p3[i].equalsIgnoreCase(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                for (int i = 0; i < UkrVoc.six_vocab_p4.length; i++) {
                    if (UkrVoc.six_vocab_p4[i].equalsIgnoreCase(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                break;
            case 7:
                for (int i = 0; i < UkrVoc.seven_vocab_p1.length; i++) {
                    if (UkrVoc.seven_vocab_p1[i].equalsIgnoreCase(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                for (int i = 0; i < UkrVoc.seven_vocab_p2.length; i++) {
                    if (UkrVoc.seven_vocab_p2[i].equalsIgnoreCase(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                for (int i = 0; i < UkrVoc.seven_vocab_p3.length; i++) {
                    if (UkrVoc.seven_vocab_p3[i].equalsIgnoreCase(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                for (int i = 0; i < UkrVoc.seven_vocab_p4.length; i++) {
                    if (UkrVoc.seven_vocab_p4[i].equalsIgnoreCase(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                for (int i = 0; i < UkrVoc.seven_vocab_p5.length; i++) {
                    if (UkrVoc.seven_vocab_p5[i].equalsIgnoreCase(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                for (int i = 0; i < UkrVoc.seven_vocab_p6.length; i++) {
                    if (UkrVoc.seven_vocab_p6[i].equalsIgnoreCase(currWord)) {
                        does_exist = true;
                        break;
                    }
                }
                break;
        }
        return does_exist;
    }

    @Override
    public void onBackPressed() {
        if (stat_container.getVisibility() == View.VISIBLE) {
            stat_container.setVisibility(View.GONE);
        } else if (rules_container.getVisibility() == View.VISIBLE) {
            rules_container.setVisibility(View.GONE);
        }
    }

    private void review() {
//        manager.requestReviewFlow().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                reviewInfo = task.getResult();
//                manager.launchReviewFlow(MainActivity.this, reviewInfo)
//                        .addOnFailureListener(e -> {
//                        })
//                        .addOnCompleteListener(task1 -> {
//                        });
//            }
//        }).addOnFailureListener(e -> {
//        });
        String url = "https://play.google.com/store/apps/details?id=com.daniel.obba";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private String monthsTranslate(String str) {
        String[] months_ukr = {
                "січня",
                "лютого",
                "березня",
                "квітня",
                "травня",
                "червня",
                "липня",
                "серпня",
                "вересня",
                "жовтня",
                "листопада",
                "грудня"};
        switch (str.substring(3, 6)) {
            case "Jan":
                str = str.substring(0, 3) + months_ukr[0] + str.substring(6);
                break;
            case "Feb":
                str = str.substring(0, 3) + months_ukr[1] + str.substring(6);
                break;
            case "Mar":
                str = str.substring(0, 3) + months_ukr[2] + str.substring(6);
                break;
            case "Apr":
                str = str.substring(0, 3) + months_ukr[3] + str.substring(6);
                break;
            case "May":
                str = str.substring(0, 3) + months_ukr[4] + str.substring(6);
                break;
            case "Jun":
                str = str.substring(0, 3) + months_ukr[5] + str.substring(6);
                break;
            case "Jul":
                str = str.substring(0, 3) + months_ukr[6] + str.substring(6);
                break;
            case "Aug":
                str = str.substring(0, 3) + months_ukr[7] + str.substring(6);
                break;
            case "Sep":
                str = str.substring(0, 3) + months_ukr[8] + str.substring(6);
                break;
            case "Oct":
                str = str.substring(0, 3) + months_ukr[9] + str.substring(6);
                break;
            case "Nov":
                str = str.substring(0, 3) + months_ukr[10] + str.substring(6);
                break;
            case "Dec":
                str = str.substring(0, 3) + months_ukr[11] + str.substring(6);
                break;
        }
        return str;
    }

    private void createLine() {
        String[] abc = {
                "а", "б", "в", "г", "д", "е", "є", "ж", "з", "и", "й",
                "і", "ї", "к", "л", "м", "н", "о", "п", "р", "с", "т",
                "у", "ф", "х", "ц", "ч", "ш", "щ", "ь", "ю", "я"
        };

        for (int i = 0; i < letters.length; i++) {
            letters[i].setText("");
        }

        String[] seven_l = new String[4];
        String[] six_l = new String[10];
        String[] five_l = new String[8];
        Random r = new Random();

        for (int i = 0; i < seven_l.length; i++) {
            String[] s = {
                    UkrVoc.seven_vocab_p1[r.nextInt(UkrVoc.seven_vocab_p1.length)],
                    UkrVoc.seven_vocab_p2[r.nextInt(UkrVoc.seven_vocab_p2.length)],
                    UkrVoc.seven_vocab_p3[r.nextInt(UkrVoc.seven_vocab_p3.length)],
                    UkrVoc.seven_vocab_p4[r.nextInt(UkrVoc.seven_vocab_p4.length)],
                    UkrVoc.seven_vocab_p5[r.nextInt(UkrVoc.seven_vocab_p5.length)],
                    UkrVoc.seven_vocab_p6[r.nextInt(UkrVoc.seven_vocab_p6.length)]
            };
            seven_l[i] = s[r.nextInt(s.length)];
        }
        for (int i = 0; i < six_l.length; i++) {
            String[] s = {
                    UkrVoc.six_vocab_p1[r.nextInt(UkrVoc.six_vocab_p1.length)],
                    UkrVoc.six_vocab_p2[r.nextInt(UkrVoc.six_vocab_p2.length)],
                    UkrVoc.six_vocab_p3[r.nextInt(UkrVoc.six_vocab_p3.length)],
                    UkrVoc.six_vocab_p4[r.nextInt(UkrVoc.six_vocab_p4.length)]
            };
            six_l[i] = s[r.nextInt(s.length)];
        }
        for (int i = 0; i < five_l.length; i++) {
            String[] s = {
                    UkrVoc.five_vocab_p1[r.nextInt(UkrVoc.five_vocab_p1.length)],
                    UkrVoc.five_vocab_p2[r.nextInt(UkrVoc.five_vocab_p2.length)],
            };
            five_l[i] = s[r.nextInt(s.length)];
        }

        for (int i = 0; i < seven_l.length; i++) {
            int finalI = 0;
            String[] available = new String[8];
            for (int j = 0; j < 7; j++) {
                if (j == 0) {
                    finalI = r.nextInt(letters.length);
                    TextView test = letters[finalI];
                    while (!test.getText().toString().isEmpty()) {
                        finalI = r.nextInt(letters.length);
                        test = letters[finalI];
                    }
                    test.setText(seven_l[i].charAt(j) + "");
                } else {
                    if (finalI % 12 == 11) {
                        if (finalI == 11) {
                            try {
                                if (letters[finalI - 1].getText().toString().isEmpty()) {
                                    available[3] = String.valueOf(finalI - 1);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 11].getText().toString().isEmpty()) {
                                    available[5] = String.valueOf(finalI + 11);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 12].getText().toString().isEmpty()) {
                                    available[6] = String.valueOf(finalI + 12);
                                }
                            } catch (Exception ignored) {
                            }
                        } else if (finalI == 143) {
                            try {
                                if (letters[finalI - 13].getText().toString().isEmpty()) {
                                    available[0] = String.valueOf(finalI - 13);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 12].getText().toString().isEmpty()) {
                                    available[1] = String.valueOf(finalI - 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 1].getText().toString().isEmpty()) {
                                    available[3] = String.valueOf(finalI - 1);
                                }
                            } catch (Exception ignored) {
                            }
                        } else {
                            try {
                                if (letters[finalI - 13].getText().toString().isEmpty()) {
                                    available[0] = String.valueOf(finalI - 13);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 12].getText().toString().isEmpty()) {
                                    available[1] = String.valueOf(finalI - 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 1].getText().toString().isEmpty()) {
                                    available[3] = String.valueOf(finalI - 1);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 11].getText().toString().isEmpty()) {
                                    available[5] = String.valueOf(finalI + 11);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 12].getText().toString().isEmpty()) {
                                    available[6] = String.valueOf(finalI + 12);
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    } else if (finalI % 12 == 0) {
                        if (finalI == 0) {
                            try {
                                if (letters[finalI + 1].getText().toString().isEmpty()) {
                                    available[4] = String.valueOf(finalI + 1);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 12].getText().toString().isEmpty()) {
                                    available[6] = String.valueOf(finalI + 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 13].getText().toString().isEmpty()) {
                                    available[7] = String.valueOf(finalI + 13);
                                }
                            } catch (Exception ignored) {
                            }
                        } else if (finalI == 132) {
                            try {
                                if (letters[finalI - 12].getText().toString().isEmpty()) {
                                    available[1] = String.valueOf(finalI - 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 11].getText().toString().isEmpty()) {
                                    available[2] = String.valueOf(finalI - 11);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 1].getText().toString().isEmpty()) {
                                    available[4] = String.valueOf(finalI + 1);
                                }
                            } catch (Exception ignored) {
                            }
                        } else {
                            try {
                                if (letters[finalI - 11].getText().toString().isEmpty()) {
                                    available[2] = String.valueOf(finalI - 11);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI - 12].getText().toString().isEmpty()) {
                                    available[1] = String.valueOf(finalI - 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 1].getText().toString().isEmpty()) {
                                    available[4] = String.valueOf(finalI + 1);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 12].getText().toString().isEmpty()) {
                                    available[6] = String.valueOf(finalI + 12);
                                }
                            } catch (Exception ignored) {
                            }
                            try {
                                if (letters[finalI + 13].getText().toString().isEmpty()) {
                                    available[7] = String.valueOf(finalI + 13);
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    } else {
                        try {
                            if (letters[finalI - 13].getText().toString().isEmpty()) {
                                available[0] = String.valueOf(finalI - 13);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI - 12].getText().toString().isEmpty()) {
                                available[1] = String.valueOf(finalI - 12);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI - 11].getText().toString().isEmpty()) {
                                available[2] = String.valueOf(finalI - 11);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI - 1].getText().toString().isEmpty()) {
                                available[3] = String.valueOf(finalI - 1);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI + 1].getText().toString().isEmpty()) {
                                available[4] = String.valueOf(finalI + 1);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI + 11].getText().toString().isEmpty()) {
                                available[5] = String.valueOf(finalI + 11);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI + 12].getText().toString().isEmpty()) {
                                available[6] = String.valueOf(finalI + 12);
                            }
                        } catch (Exception ignored) {
                        }
                        try {
                            if (letters[finalI + 13].getText().toString().isEmpty()) {
                                available[7] = String.valueOf(finalI + 13);
                            }
                        } catch (Exception ignored) {
                        }
                    }
                    int random = r.nextInt(available.length);
                    while (available[random] == null) {
                        random = r.nextInt(available.length);
                    }
                    letters[Integer.parseInt(available[random])].setText(seven_l[i].charAt(j) + "");
                    finalI = Integer.parseInt(available[random]);
                    Arrays.fill(available, null);
                }
            }
            String s = "s";
        }

    }
}
