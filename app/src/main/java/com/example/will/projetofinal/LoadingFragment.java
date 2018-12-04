package com.example.will.projetofinal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LoadingFragment extends Fragment implements ICallbackReceiver {

    private TextView label;
    private ICallbackReceiver parent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loading,container,false);

        label = view.findViewById(R.id.loadingLabel);
        label.setText("Requisitando API");

        if (parent.isLoggedin())
        {
            FacebookRequests.getInstance().getUserEvents(this);
        }
        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if(!(context instanceof ICallbackReceiver))
        {
            throw new RuntimeException("Deve ser um ICallbackReceiver");
        }
        parent = (ICallbackReceiver) context;
    }

    @Override
    public void onEndProccess(String result) {
        label.setText("Carregando informações");
        parent.onEndProccess(result);
    }

    @Override
    public boolean isLoggedin() {
        return true;
    }
}
