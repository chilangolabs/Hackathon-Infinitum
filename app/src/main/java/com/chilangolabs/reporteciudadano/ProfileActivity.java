package com.chilangolabs.reporteciudadano;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chilangolabs.reporteciudadano.customelements.MontserratButton;
import com.chilangolabs.reporteciudadano.customelements.MontserratTextView;
import com.chilangolabs.reporteciudadano.singleton.UserSingleton;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    CarouselView carouselView;
    MontserratTextView txtProfileName;
    CircleImageView circleImageViewprofile;
    MontserratButton btnProfileNewReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtProfileName = (MontserratTextView) findViewById(R.id.txtProfileName);
        circleImageViewprofile = (CircleImageView) findViewById(R.id.imgProfile);
        btnProfileNewReport = (MontserratButton) findViewById(R.id.btnProfileNewReport);

        final int[] sampleimages = {R.mipmap.bache_birthday, R.mipmap.reporte_basura, R.mipmap.reporte_fuga_agua, R.mipmap.alumbrado, R.mipmap.reporte_bache};
        final String[] titles = {"Reporte de bache", "Reporte de basura", "Reporte de fuga de agua", "Reporte de alumbrado público", "Reporte de bache"};
        final String[] descrip = {"Chihuahua 230 - Estado: Revisado", "Chapultepec 70 - Estado: En Transcurso", "Reforma 300 - Estado: Reportado", "Montevideo s/n - Estado: Finalizado", "Revolución 345 - Estado: Finalizado"};
        final String[] tickets = {"#303021", "#23513", "#098802", "#872340", "#948239"};

        txtProfileName.setText(UserSingleton.getName());

        Log.e("name------>", UserSingleton.getName() + " " + UserSingleton.getImg());

        Glide.with(ProfileActivity.this).load(UserSingleton.getImg()).into(circleImageViewprofile);
//        Picasso.with(this).load(UserSingleton.getImg()).into(circleImageViewprofile);

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleimages.length);

        carouselView.setViewListener(new ViewListener() {
            @Override
            public View setViewForPosition(int position) {
                View customView = getLayoutInflater().inflate(R.layout.item_carousel, null);
                Holder holder = new Holder();
                holder.txtTitle = (MontserratTextView) customView.findViewById(R.id.txtItemCarouselTitle);
                holder.txtDesc = (MontserratTextView) customView.findViewById(R.id.txtItemCarouselDesc);
                holder.txtTicket = (MontserratTextView) customView.findViewById(R.id.txtItemCarouselTicket);
                holder.imgBG = (ImageView) customView.findViewById(R.id.imgItemCard);

                holder.txtTitle.setText(titles[position]);
                holder.txtDesc.setText(descrip[position]);
                holder.txtTicket.setText(tickets[position]);
                holder.imgBG.setImageResource(sampleimages[position]);

                customView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(ProfileActivity.this, ReportActivity.class));
                    }
                });

                return customView;
            }
        });

        btnProfileNewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, AltaReporteActivity.class));
            }
        });

    }

    public class Holder {
        MontserratTextView txtTitle, txtDesc, txtTicket;
        ImageView imgBG;
    }

}
