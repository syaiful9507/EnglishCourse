package syaiful.finalpro.englishcourse.pdmodel;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by syaiful9508 on 31/07/17.
 */

public class pdmodel {
    static ProgressDialog progressDialog;

    public static void pdMenyiapkanDataJadwal(Context context){
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Menyiapkan data jadwal...");
        progressDialog.setTitle("Silahkan Tunggu");
        progressDialog.show();

    }
}
