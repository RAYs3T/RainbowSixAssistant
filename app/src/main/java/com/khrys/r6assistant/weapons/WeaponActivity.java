package com.khrys.r6assistant.weapons;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.khrys.r6assistant.R;
import com.khrys.r6assistant.operators.OperatorActivity;

/*
 * Created by Chrysler on 3/10/2017.
 * RainbowSixAssistant
*/

public class WeaponActivity extends AppCompatActivity
{

    ImageView imgRookArmor, imgArmor1, imgArmor2, imgArmor3;
    TextView txtArmorL1,txtArmorB1,txtArmorL2, txtArmorB2, txtArmorL3, txtArmorB3;
    String[] statsWeapon;
    LinearLayout layoutBarrel1,layoutBarrel2,layoutBarrel3,layoutBarrel4,layoutBarrel5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stats);

        if(getSupportActionBar()!= null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(0);
        }

        //Header
        TextView txtNomArme = findViewById(R.id.txtNomArme);
        ImageView imgViewArme = findViewById(R.id.imgArme);

        //Operators image
        ImageView imgViewOp1 = findViewById(R.id.imgAgent1);
        ImageView imgViewOp2 = findViewById(R.id.imgAgent2);
        ImageView imgViewOp3 = findViewById(R.id.imgAgent3);
        ImageView imgViewOp4 = findViewById(R.id.imgAgent4);
        ImageView imgViewOp5 = findViewById(R.id.imgAgent5);

        //Basic informations
        TextView txtViewAmmo = findViewById(R.id.txtAmmo);
        TextView txtViewFirerate = findViewById(R.id.txtFirerate);
        TextView txtViewDamagefall = findViewById(R.id.txtDamagefalloff);

        /*
            ************
            Damage stats
        */

        //Rook Amor
        Switch switchRookArmor = findViewById(R.id.switchRookarmor);
        imgRookArmor = findViewById(R.id.imgRookarmor);

        //Armor image
        imgArmor1 = findViewById(R.id.imgArmor1);
        imgArmor2 = findViewById(R.id.imgArmor2);
        imgArmor3 = findViewById(R.id.imgArmor3);

        //Armor text
        txtArmorL1 = findViewById(R.id.txtArmorL1);
        txtArmorB1 = findViewById(R.id.txtArmorB1);
        txtArmorL2 = findViewById(R.id.txtArmorL2);
        txtArmorB2 = findViewById(R.id.txtArmorB2);
        txtArmorL3 = findViewById(R.id.txtArmorL3);
        txtArmorB3 = findViewById(R.id.txtArmorB3);

        //Barrel Layouts
        layoutBarrel1 = findViewById(R.id.layoutBarrel1);
        layoutBarrel2 = findViewById(R.id.layoutBarrel2);
        layoutBarrel3 = findViewById(R.id.layoutBarrel3);
        layoutBarrel4 = findViewById(R.id.layoutBarrel4);
        layoutBarrel5 = findViewById(R.id.layoutBarrel5);

        //Barrel infos
        ImageView imgBarrel1 = findViewById(R.id.imgBarrel1);
        ImageView imgBarrel2 = findViewById(R.id.imgBarrel2);
        ImageView imgBarrel3 = findViewById(R.id.imgBarrel3);
        ImageView imgBarrel4 = findViewById(R.id.imgBarrel4);
        ImageView imgBarrel5 = findViewById(R.id.imgBarrel5);

        TextView txtBarrel1 = findViewById(R.id.txtBarrel1);
        TextView txtBarrel2 = findViewById(R.id.txtBarrel2);
        TextView txtBarrel3 = findViewById(R.id.txtBarrel3);
        TextView txtBarrel4 = findViewById(R.id.txtBarrel4);
        TextView txtBarrel5 = findViewById(R.id.txtBarrel5);

        /*
            ************
        */

        String armeId = getIntent().getStringExtra("arme");

        String imgArmeId = "g_"+armeId;
        imgArmeId = imgArmeId.replace('-','_');
        imgArmeId = imgArmeId.replace(' ','_');

        int resID = getResources().getIdentifier(imgArmeId, "drawable", getPackageName());

        imgViewArme.setImageResource(resID);
        txtNomArme.setText(armeId);


        int arrayId = getResources().getIdentifier(imgArmeId, "array", getApplicationContext().getPackageName());
        statsWeapon = getResources().getStringArray(arrayId);

        setupAgent(0,imgViewOp1);
        setupAgent(1,imgViewOp2);
        setupAgent(2,imgViewOp3);
        setupAgent(3,imgViewOp4);
        setupAgent(4,imgViewOp5);

        if(statsWeapon[5].equals("0"))
        {
            txtViewAmmo.setText("-");
        }
        else
        {
            txtViewAmmo.setText(String.valueOf(statsWeapon[5]));
        }

        if(statsWeapon[6].equals("0"))
        {
            txtViewFirerate.setText("-");
        }
        else
        {
            txtViewFirerate.setText(String.valueOf(statsWeapon[6]));
        }

        if(String.valueOf(statsWeapon[7]).equals(""))
        {
            txtViewDamagefall.setText("-");
        }
        else
        {
            String txtDamage = statsWeapon[7]+"m";
            txtViewDamagefall.setText(txtDamage);
        }

        switchTextArmor(0);

        if(statsWeapon[14].equals("0"))
        {
            switchRookArmor.setVisibility(View.INVISIBLE);
            imgRookArmor.setVisibility(View.INVISIBLE);
        }

        switchRookArmor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {

                int drawRook, draw1, draw2, draw3;
                if(b)
                {
                    drawRook = R.drawable.o_rook;
                    draw1 = R.drawable.armor_rook_light;
                    draw2 = R.drawable.armor_rook_medium;
                    draw3 = R.drawable.armor_rook_strong;
                    switchTextArmor(1);
                }
                else
                {
                    drawRook = R.drawable.o_rook_bw;
                    draw1 = R.drawable.ostat_armor_1;
                    draw2 = R.drawable.ostat_armor_2;
                    draw3 = R.drawable.ostat_armor_3;
                    switchTextArmor(0);
                }

                imgRookArmor.setImageResource(drawRook);
                imgArmor1.setImageResource(draw1);
                imgArmor2.setImageResource(draw2);
                imgArmor3.setImageResource(draw3);

            }
        });

        setupBarrel(20, layoutBarrel1, imgBarrel1, txtBarrel1);
        setupBarrel(21, layoutBarrel2, imgBarrel2, txtBarrel2);
        setupBarrel(22, layoutBarrel3, imgBarrel3, txtBarrel3);
        setupBarrel(23, layoutBarrel4, imgBarrel4, txtBarrel4);
        setupBarrel(24, layoutBarrel5, imgBarrel5, txtBarrel5);
    }

    void switchTextArmor(int type)
    {
        if(type == 0)
        {
            txtArmorL1.setText(String.valueOf(statsWeapon[8]));
            txtArmorB1.setText(String.valueOf(statsWeapon[9]));
            txtArmorL2.setText(String.valueOf(statsWeapon[10]));
            txtArmorB2.setText(String.valueOf(statsWeapon[11]));
            txtArmorL3.setText(String.valueOf(statsWeapon[12]));
            txtArmorB3.setText(String.valueOf(statsWeapon[13]));
        }
        else
        {
            txtArmorL1.setText(String.valueOf(statsWeapon[14]));
            txtArmorB1.setText(String.valueOf(statsWeapon[15]));
            txtArmorL2.setText(String.valueOf(statsWeapon[16]));
            txtArmorB2.setText(String.valueOf(statsWeapon[17]));
            txtArmorL3.setText(String.valueOf(statsWeapon[18]));
            txtArmorB3.setText(String.valueOf(statsWeapon[19]));
        }
    }

    void setupAgent(int type, ImageView imgV)
    {
        if(!statsWeapon[type].equals(""))
        {
            String opeB = String.valueOf(statsWeapon[type]);
            String op = "o_"+opeB;
            int imgid = getResources().getIdentifier(op, "drawable", getPackageName());
            imgV.setImageResource(imgid);
            imgV.setOnClickListener(new clickImgOperator(opeB));
        }
        else
        {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
            imgV.getLayoutParams();
            params.weight = 0.0f;
            imgV.setLayoutParams(params);
            imgV.getLayoutParams().width= ViewGroup.LayoutParams.WRAP_CONTENT;
        }
    }

    void setupBarrel(int type, LinearLayout layoutV, ImageView imgV, TextView txtV)
    {
        if(!statsWeapon[type].equals(""))
        {

            String finaltxt = String.valueOf(statsWeapon[type]);
            int txtId = getResources().getIdentifier(finaltxt, "string", getPackageName());
            txtV.setText(getResources().getString(txtId).toUpperCase());

            String txt = String.valueOf(statsWeapon[type]);
            String bar = "gb_"+txt;
            int imgid = getResources().getIdentifier(bar, "drawable", getPackageName());
            imgV.setImageResource(imgid);
        }
        else
        {
            layoutV.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:

                this.finish();
                return true;

            case R.id.action_help:
                startActivity(new Intent(this, HelpStatsActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class clickImgOperator implements View.OnClickListener
    {
        String operator = "";

        clickImgOperator(String operator)
        {
            super();
            this.operator = operator;
        }

        @Override
        public void onClick(View view)
        {
            Context context = view.getContext();
            Intent newOperator = new Intent(context, OperatorActivity.class);
            newOperator.putExtra("operator", operator);
            context.startActivity(newOperator);
        }
    }
}