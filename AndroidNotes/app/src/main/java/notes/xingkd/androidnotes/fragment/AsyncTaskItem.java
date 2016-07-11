package notes.xingkd.androidnotes.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import notes.xingkd.androidnotes.R;

/**
 * Created by xkd on 16-7-6.
 */
public class AsyncTaskItem extends LinearLayout {
    private TextView mTitle;
    private ProgressBar mProgress;

    public AsyncTaskItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public AsyncTaskItem(Context context) {
        super(context);
    }

    public void setTitle(String title) {
        if (mTitle == null) {
            mTitle = (TextView) findViewById(R.id.task_name);
        }
        mTitle.setText(title);
    }

    public void setProgress(int prog) {
        if (mProgress == null) {
            mProgress = (ProgressBar) findViewById(R.id.task_progress);
        }
        mProgress.setProgress(prog);
    }
}
