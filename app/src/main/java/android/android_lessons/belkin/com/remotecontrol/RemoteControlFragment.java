package android.android_lessons.belkin.com.remotecontrol;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Belkin on 26.04.2015.
 */
public class RemoteControlFragment extends Fragment {
    private TextView mSelectedTextView;
    private TextView mWorkingTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_remote_control, container, false);

        mSelectedTextView = (TextView)v.findViewById(R.id.fragment_remote_control_selectedTextView);
        mWorkingTextView = (TextView)v.findViewById(R.id.fragment_remote_control_workingTextView);

        View.OnClickListener numberButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView)view;
                String working = mWorkingTextView.getText().toString();
                String text = textView.getText().toString();
                if(working.equals("0")){
                    mWorkingTextView.setText(text);
                }else{
                    mWorkingTextView.setText(working + text);
                }
            }
        };

        TableLayout tableLayout = (TableLayout)v.findViewById(R.id.fragment_remote_control_tableLayout);
        int number = 1;
        for (int i = 2; i < tableLayout.getChildCount() - 1; i++) {
            TableRow row = (TableRow)tableLayout.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                Button button = (Button)row.getChildAt(j);
                button.setText("" + number);
                button.setOnClickListener(numberButtonListener);
                number++;
            }
        }

        TableRow bottonRow = (TableRow)tableLayout.getChildAt(tableLayout.getChildCount()-1);

        Button deleteButton = (Button)bottonRow.getChildAt(0);
        deleteButton.setText("Delete");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWorkingTextView.setText("0");
            }
        });
        deleteButton.setTextAppearance(getActivity().getApplicationContext(), R.style.ActionButton);

        Button zeroButton = (Button)bottonRow.getChildAt(1);
        zeroButton.setText("0");
        zeroButton.setOnClickListener(numberButtonListener);

        Button enterButton = (Button)bottonRow.getChildAt(2);
        enterButton.setText("Enter");
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence working = mWorkingTextView.getText();
                if(working.length()>0){
                    mSelectedTextView.setText(working);
                }
                mWorkingTextView.setText("0");
            }
        });
        enterButton.setTextAppearance(getActivity().getApplicationContext(), R.style.ActionButton);

        return v;
    }
}
