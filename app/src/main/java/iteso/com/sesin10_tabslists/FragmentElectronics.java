package iteso.com.sesin10_tabslists;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentElectronics.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentElectronics#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentElectronics extends android.support.v4.app.Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_electronics, container, false);
        return view;
    }
}
