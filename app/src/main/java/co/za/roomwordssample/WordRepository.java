package co.za.roomwordssample;

import android.app.Application;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    public void insert(Word word){
        new insertAsyncTask(mWordDao).execute(word);
    }

    public void update(Word word){
        new updateAsyncTask(mWordDao).execute(word);
    }

    public void deleteAll(){
        new deleteAllAsyncTask(mWordDao).execute();
    }

    public void delete(Word word){
        new deleteAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void>{

        private WordDao mAsyncTaskDao;
        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }

        public insertAsyncTask(WordDao wordDao){
            this.mAsyncTaskDao = wordDao;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Word, Void, Void>{

        private WordDao mAsyncTaskDao;
        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.update(words[0]);
            return null;
        }

        public updateAsyncTask(WordDao wordDao){
            this.mAsyncTaskDao = wordDao;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void>{

        private WordDao mAsyncTaskDao;
        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }

        public deleteAllAsyncTask(WordDao wordDao){
            this.mAsyncTaskDao = wordDao;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Word, Void, Void>{

        private WordDao mAsyncTaskDao;
        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.delete(words[0]);
            return null;
        }

        public deleteAsyncTask(WordDao wordDao){
            this.mAsyncTaskDao = wordDao;
        }
    }

}
