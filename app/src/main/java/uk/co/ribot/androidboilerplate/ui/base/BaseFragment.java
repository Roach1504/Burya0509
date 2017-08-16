package uk.co.ribot.androidboilerplate.ui.base;

import android.app.Fragment;

import uk.co.ribot.androidboilerplate.di.IHasComponent;


public abstract class BaseFragment extends Fragment {
    @SuppressWarnings("unchecked")
    protected <T> T getComponent(Class<T> componentType) {
        return componentType.cast(((IHasComponent<T>)getActivity()).getComponent());
    }
}
