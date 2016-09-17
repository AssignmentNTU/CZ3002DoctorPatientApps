package ntu.com.mylife.view;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import ntu.com.mylife.R;
import ntu.com.mylife.common.service.chatCallback;
import ntu.com.mylife.common.service.DatabaseDaoUserScheduleImpl;
import ntu.com.mylife.common.service.DatabaseUserScheduleDao;
import ntu.com.mylife.common.entity.databaseentity.CurrentScheduleRecyclerViewAdaptor;


public class HomeView extends Fragment implements chatCallback {

    private OnFragmentInteractionListener mListener;
    private TextView todaySchedule,todayNumberSchedule,todayMonthSchedule;
    private RecyclerView mRecyclerView;
    private DatabaseUserScheduleDao dbScehdule;
    private LinearLayoutManager mLayoutManager;

    public HomeView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this.getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView  =  inflater.inflate(R.layout.fragment_home_view, container, false);
        todaySchedule = (TextView) rootView.findViewById(R.id.today_fragment_day_main_page);
        todayNumberSchedule = (TextView) rootView.findViewById(R.id.today_fragment_number_day_main_page);
        todayMonthSchedule = (TextView) rootView.findViewById(R.id.today_fragment_month_main_page);
        todaySchedule.setTextColor(Color.parseColor("#009688"));
        todayNumberSchedule.setTextColor(Color.parseColor("#009688"));
        todayMonthSchedule.setTextColor(Color.parseColor("#009688"));
        instanstiateTodaySchedule();

        //instanstiate current schedule
        dbScehdule = new DatabaseDaoUserScheduleImpl(this.getActivity(),this,"edward454","14-December-2016");
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.current_schedule_recyler_view);
        CurrentScheduleRecyclerViewAdaptor adaptor;
        adaptor = new CurrentScheduleRecyclerViewAdaptor(new ArrayList<String>(), new ArrayList<String>());
        mRecyclerView.setAdapter(adaptor);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        return rootView;
    }


    private  void instanstiateTodaySchedule(){

        Calendar rightNow = Calendar.getInstance();
        String month =  new SimpleDateFormat("MMMM").format(rightNow.getTime());
        String weekDay = new SimpleDateFormat("EEEE", Locale.US).format(rightNow.getTime());
        int dayNumber = rightNow.getTime().getDate();
        todaySchedule.setText(weekDay);
        todayNumberSchedule.setText(dayNumber+"");
        todayMonthSchedule.setText(month);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void callbackFunction(Object object) {
        HashMap hashReturned = (HashMap) object;
        CurrentScheduleRecyclerViewAdaptor adaptor;
        adaptor = new CurrentScheduleRecyclerViewAdaptor((ArrayList)hashReturned.get("listTime"),(ArrayList)hashReturned.get("listMessage"));
        mRecyclerView.setAdapter(adaptor);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
