package hcmute.spkt.hoanhtu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private EditText edScreen;
    private TextView num1,num2,num3,num4,num5,num6,num7,num8,num9,num0;
    private TextView dot,cong,tru,nhan,chia,bang,txtC;

    private boolean flag=true,flag1=true,flag2=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findID();
        showNumber(num0);
        showNumber(num1);
        showNumber(num2);
        showNumber(num3);
        showNumber(num4);
        showNumber(num5);
        showNumber(num6);
        showNumber(num7);
        showNumber(num7);
        showNumber(num8);
        showNumber(num9);
        showDot(dot);
        showOperator(cong);
        showOperator(tru);
        showOperator(nhan);
        showOperator(chia);

        txtC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edScreen.setText("");
            }
        });

        Equals();

    }


    private void findID()
    {
        edScreen=(EditText)findViewById(R.id.screen);
        num0=(TextView)findViewById(R.id.num0);
        num1=(TextView)findViewById(R.id.num1);
        num2=(TextView)findViewById(R.id.num2);
        num3=(TextView)findViewById(R.id.num3);
        num4=(TextView)findViewById(R.id.num4);
        num5=(TextView)findViewById(R.id.num5);
        num6=(TextView)findViewById(R.id.num6);
        num7=(TextView)findViewById(R.id.num7);
        num8=(TextView)findViewById(R.id.num8);
        num9=(TextView)findViewById(R.id.num9);
        dot=(TextView)findViewById(R.id.dot);
        cong=(TextView)findViewById(R.id.sumation);
        tru=(TextView)findViewById(R.id.subtraction);
        nhan=(TextView)findViewById(R.id.multiplication);
        chia=(TextView)findViewById(R.id.division);
        bang=(TextView)findViewById(R.id.equal);
        txtC=(TextView)findViewById(R.id.c);

    }
    private void showNumber(final TextView num)
    {
        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edScreen.setText(edScreen.getText()+num.getText().toString());
                flag=true;
            }
        });
    }

    private void showOperator(final TextView operator) {


                operator.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(flag) {
                            edScreen.setText(edScreen.getText() + operator.getText().toString());
                            flag1=true;
                            flag = false;
                        }
                    }
                });


    }

    private void showDot(final TextView dot)
    {
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag1) {
                    edScreen.setText(edScreen.getText() + dot.getText().toString());
                    flag=true;
                    flag1=false;
                }
            }
        });
    }


    private void Equals()
    {
        bang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Separate();
            }
        });
    }
    private boolean Separate()
    {
        String S =edScreen.getText().toString()+"e";
        String a="";
        String b="";
        String[]c = new String[100];
        List<String>listNum=new ArrayList<String>();
        List<Character> listOperator=new ArrayList<Character>();

        int k=0;
        for(int i=0;i<S.length();i++) {
            if(S.charAt(0)=='-'&&i==0)
                b+=S.charAt(0);
            if(S.charAt(i)=='+'||S.charAt(i)=='-'||S.charAt(i)=='*'||S.charAt(i)=='/'||S.charAt(i)=='e')
            {
                if(S.charAt(i)!='e')
                listOperator.add(S.charAt(i));
                if(b!=""&&!b.equals("-"))
                {
                    if(b.equals("."))
                    {
                        edScreen.setText("sai cu phap");
                        return false;
                    }
                    listNum.add(b);
                    b="";
                }
            }
            else
            {
                b+=S.charAt(i);
            }

        }


        double total= handling(listNum,listOperator);

        edScreen.setText(String.valueOf(total));
        return true;

    }

    private double handling(List<String>a,List<Character> b)
    {
        double t=0;
        int i=0;
        while(i<b.size())
        {
            if(a.size()>b.size())
            {
                if(b.get(i)=='*')
                {
                    t=Double.valueOf(a.get(i));
                    t*=Double.valueOf(a.get(i+1));
                    a.remove(i);
                    a.set(i,String.valueOf(t));
                    b.remove(i);
                    if(b.isEmpty()||a.isEmpty())
                        return t;

                }
                else
                if(b.get(i)=='/')
                {
                    t=(Double.valueOf(a.get(i)));
                    t/=Double.valueOf(a.get(i+1));
                    a.remove(i);
                    a.set(i,String.valueOf(t));
                    b.remove(i);
                    if(b.isEmpty()||a.isEmpty())
                        return t;

                }
                else i++;
            }
            else {
                b.remove(0);

            }
        }

        i=0;
        while (i<b.size())
        {
            if(a.size()>b.size())
            {
                if(b.get(i)=='+')
                {
                    t=Double.valueOf(a.get(i))+Double.valueOf(a.get(i+1));
                    a.remove(i);
                    a.set(i,String.valueOf(t));
                    b.remove(i);
                    if(b.isEmpty()||a.isEmpty())
                        return t;

                }
                else
                if(b.get(i)=='-')
                {
                    t=Double.valueOf(a.get(i))-Double.valueOf(a.get(i+1));
                    a.remove(i);
                    a.set(i,String.valueOf(t));
                    b.remove(i);
                    if(b.isEmpty()||a.isEmpty())
                        return t;

                }
                else i++;
            }
            else
            {
                b.remove(0);
            }
        }
        return t;

    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
