package felipe.sp.br.pokeclic.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.view.View;
import android.widget.ProgressBar;

import felipe.sp.br.pokeclic.Main;

/**
 * Created by Felipe on 14/09/2018.
 */
//Peguei essa classe de outro projeto apenas para controlar a animação de load
public class BarraProgresso {
    private static BarraProgresso INSTANCE = new BarraProgresso();

    public static BarraProgresso getInstance(){
        return  INSTANCE;
    }

    void showProgress(final boolean show, final ProgressBar mProgressView) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = Main.getContext().getResources().getInteger(android.R.integer.config_shortAnimTime);


            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);

        }
    }
}
