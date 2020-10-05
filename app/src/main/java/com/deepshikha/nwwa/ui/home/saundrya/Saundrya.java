package com.deepshikha.nwwa.ui.home.saundrya;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.deepshikha.nwwa.R;
import com.deepshikha.nwwa.model.SaundryaServices;
import com.deepshikha.nwwa.ui.samudri.CustomAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class Saundrya extends Fragment implements SaundryaAdapter.ItemClickListener, SaundryaAdapter.AppointmentListInterface {
    private static final String TAG = "Saundrya";
//ui
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    Button book,request;
    List<SaundryaServices> pl;
    private int dotscount;
    private ImageView[] dots;
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    ProgressBar pb;
    Integer[] imageId = {R.drawable.saundrya1, R.drawable.saundrya2, R.drawable.saundrya3};
    String[] imagesName = {"image1","image2","image3"};
    public  ArrayList<String> bookingList;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
//   firebase
    private FirebaseDatabase database;
    private DatabaseReference dbRefUserPosts,dbRef,dbRefAppointments,dbRefUserReq;
    private static FirebaseUser currentUser;
    FirebaseStorage storage;
    StorageReference storageReference;


//    ui
    RecyclerView rv;
    SaundryaAdapter adapter;
    TextView name,phone;
    ListView list;
    TextInputEditText date;

//    vars
    String username,phoneno,timing,timeOfRequest;
    public Saundrya() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_saundrya, container, false);
        pb = root.findViewById(R.id.pb);
        book = root.findViewById(R.id.book);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookAppointmentButton();
            }
        });
        //slide show
        ImageDisplay(root);

        recyclerView(root);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseStorageReference();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        pb.setVisibility(View.VISIBLE);

//        slide show
        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

            dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

//                    for(int i = 0; i< dotscount; i++){
//
//                        dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.non_active_dot));
//                    }
//
//                    dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dot));

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            /*After setting the adapter use the timer */
            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == 4) {
                        currentPage = 0;
                    }
                    viewPager.setCurrentItem(currentPage++, true);
                }
            };

            timer = new Timer(); // This will create a new Thread
            timer.schedule(new TimerTask() { // task to be scheduled
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, DELAY_MS, PERIOD_MS);


        }

    }
//view pager auto scrolling images
    public void ImageDisplay(View root){
        sliderDotspanel = root.findViewById(R.id.SliderDots1);
        viewPager = root.findViewById(R.id.viewpagerSaundrya);
        PagerAdapter adapter = new CustomAdapter(getActivity(),imageId,imagesName);
        viewPager.setAdapter(adapter);
        dotscount = adapter.getCount();
        dots = new ImageView[dotscount];
    }
    public void recyclerView(View v){
        rv = v.findViewById(R.id.saundryaRV);
        rv.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
        rv.setLayoutManager(linearLayoutManager);
//        mDataSet = new ArrayList<>();
        pl=new ArrayList<>();


    }

    public void firebaseStorageReference(){
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        database = FirebaseDatabase.getInstance();
//        refrence for saving posts
        dbRefUserPosts = database.getReference("/Saundrya");
        dbRef = database.getReference("/Saundrya/Services");
        dbRefAppointments = database.getReference("/Saundrya/Appointments");
        dbRefUserReq = database.getReference("/UserRequests");

        dbRef.addValueEventListener(changeListener);
        dbRef.addChildEventListener(childEventListener);


    }
    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//            adapter.notifyDataSetChanged();
            Log.d(TAG, "onChildAdded: ");
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    ValueEventListener changeListener = new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (isOnline(getContext())) {
                for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                    SaundryaServices post = new SaundryaServices();

                    post.setTitle((datasnapshot.child("title").getValue()).toString());
                    post.setNo(datasnapshot.child("no").getValue().toString());
                    post.setSt1(datasnapshot.child("st1").getValue().toString());

                    if (datasnapshot.child("st2").exists()) {
                        post.setSt2(datasnapshot.child("st2").getValue().toString());
                    } if (datasnapshot.child("st3").exists()) {
                        post.setSt3(datasnapshot.child("st3").getValue().toString());
                    } if (datasnapshot.child("st4").exists()) {
                        post.setSt4(datasnapshot.child("st4").getValue().toString());
                    }
                     if (datasnapshot.child("st5").exists()) {
                         post.setSt5(datasnapshot.child("st5").getValue().toString());
                    }
                     if (datasnapshot.child("st6").exists()) {
                         post.setSt6(datasnapshot.child("st6").getValue().toString());
                    }
                     if (datasnapshot.child("st7").exists()) {
                         post.setSt7(datasnapshot.child("st7").getValue().toString());
                    }
                     if (datasnapshot.child("st8").exists()) {
                         post.setSt8(datasnapshot.child("st8").getValue().toString());
                    }
                    pl.add(post);
                }

                adapter = new SaundryaAdapter(pl,Saundrya.this);
//                if (flag == 0) {
                    rv.setAdapter(adapter);
                    pb.setVisibility(View.GONE);
//                } else {
                    adapter.notifyDataSetChanged();
//                flag = 0;
//                }

            }
            else{
//                showSnackbar("No Internet ","#ff0000");
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
//            notifyUser("Database error: " + databaseError.toException());
            Log.d(TAG, "onCancelled: "+ databaseError);
        }


    };
    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
public void BookAppointmentButton(){
    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());

    View view1 = getLayoutInflater().inflate(R.layout.booking, null);

    bottomSheetDialog.setContentView(view1);

    name = view1.findViewById(R.id.namebook);
    phone = view1.findViewById(R.id.phone);
    list = view1.findViewById(R.id.list);
    date = view1.findViewById(R.id.dateee);
    request = view1.findViewById(R.id.re);

    setValuesToAppointment();
    request.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            username = name.getText().toString();
            phoneno = phone.getText().toString();
            timing = date.getText().toString();
            sendAppointmentToFirebase();
        }
    });
    bottomSheetDialog.show();

}

    private void setValuesToAppointment() {
        name.setText("Deepshikha");
        phone.setText("9599670377");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getContext(),
                R.layout.appointment_list,
                bookingList );

        list.setAdapter(arrayAdapter);
        Date currentTime = Calendar.getInstance().getTime();
        timeOfRequest = currentTime.toString();
    }

    private void sendAppointmentToFirebase() {
        String key = dbRefAppointments.child(username)
                .child(currentUser.getUid()).child(timeOfRequest).push().getKey();

        String key2 = dbRefUserReq.child(username)
                .child(currentUser.getUid()).child(timeOfRequest).push().getKey();
//        UserData post = new UserData( userData.getName() , userData.getPost(),userData.getImgUrl(), userData.getTime(),userData.getUid());
//        Map<String, String> postValues = new Map<>();
        Map<String, String> postValues = new HashMap<>();
            postValues.put("name",username);
            postValues.put("phone",phoneno);
            postValues.put("date",timing);
            postValues.put("list",bookingList.toString());
            postValues.put("timeOfRequest",timeOfRequest);
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> childUpdates2 = new HashMap<>();
        childUpdates.put("/Appointments/" + key, postValues);
        childUpdates2.put("/" + username + "/" + key2, postValues);
        dbRefAppointments.updateChildren(childUpdates);
        dbRefUserReq.updateChildren(childUpdates2);
    }

    @Override
    public void onItemClick(View view, int position) {
//        click listener for adapter clicks
    }

    @Override
    public void appointmentList(ArrayList<String> list) {
        bookingList = list;
        Log.d(TAG, "appointmentList: "+ bookingList);
    }
}
