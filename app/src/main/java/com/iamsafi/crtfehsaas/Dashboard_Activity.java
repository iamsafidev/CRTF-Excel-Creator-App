package com.iamsafi.crtfehsaas;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.iamsafi.crtfehsaas.adapter.PeopleAdapter;
import com.iamsafi.crtfehsaas.database.AppDatabase;
import com.iamsafi.crtfehsaas.database.Person;
import com.iamsafi.crtfehsaas.utils.Consts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.iamsafi.crtfehsaas.viewModel.DashboardActivityViewModel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.iamsafi.crtfehsaas.utils.Consts.directory_path;

@SuppressWarnings("deprecation")
public class Dashboard_Activity extends AppCompatActivity {
    private static final int REQUEST_STORAGE = 001;
    private List<Person> mPersonList = new ArrayList<>();
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.person_records)
    RecyclerView mRecyclerView;
    @BindView(R.id.llIcon)
    LinearLayout ll_nopersons;
    PeopleAdapter peopleAdapter;
    DashboardActivityViewModel mDashboardViewModel;

    private File file;
    private int REQUEST_SETTING=002;

    @OnClick(R.id.menuLeftIV)
    void openDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    @OnClick(R.id.fab)
    void OpenFormActivity() {
        Intent intent = new Intent(Dashboard_Activity.this, Form_Activity.class);
        startActivityForResult(intent, FORM_REQUEST_CODE);
    }

    public static final int FORM_REQUEST_CODE = 01;
    private static final String TAG_HOME = "home";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_);
        ButterKnife.bind(this);
        // initializing navigation menu
        setUpNavigationView();
        checkFileExists();
        initViewModel();
        showData();
        initRecyclerView();

    }

    private void initViewModel() {
        Observer<List<Person>> personObserver =
                new Observer<List<Person>>() {
                    @Override
                    public void onChanged(List<Person> noteEntities) {
                        mPersonList.clear();
                        mPersonList.addAll(noteEntities);
                        if (mPersonList.size() == 0) {
                            mRecyclerView.setVisibility(View.GONE);
                            ll_nopersons.setVisibility(View.VISIBLE);
                        } else {
                            mRecyclerView.setVisibility(View.VISIBLE);
                            ll_nopersons.setVisibility(View.GONE);
                        }
                        if (peopleAdapter == null) {
                            showData();
                        } else {
                            peopleAdapter.notifyDataSetChanged();
                        }
                    }
                };
        mDashboardViewModel = ViewModelProviders.of(Dashboard_Activity.this)
                .get(DashboardActivityViewModel.class);
        mDashboardViewModel.personsList.observe(Dashboard_Activity.this, personObserver);
    }

    private void showData() {
        peopleAdapter = new PeopleAdapter(Dashboard_Activity.this, mPersonList);
        mRecyclerView.setAdapter(peopleAdapter);
    }

    private void initRecyclerView() {
        mRecyclerView.hasFixedSize();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(mRecyclerView.getContext(), linearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                deletePeople(peopleAdapter.getPersonAtPosition(viewHolder.getAdapterPosition()));
                // deleteNote(notesAdapter.getNoteAtPosition(viewHolder.getAdapterPosition()));

            }
        });

        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void deletePeople(Person person) {
        mDashboardViewModel.deletePeople(person);
    }

    private void checkFileExists() {
        file = new File(Consts.directory_path);
        if (!file.exists()) {
            Log.i("File Created", String.valueOf(file.mkdirs()));
        }
    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        recreate();
                        closeDrawer();
                        break;

                    case R.id.export:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                            if (ActivityCompat.checkSelfPermission(Dashboard_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    == PackageManager.PERMISSION_GRANTED)
                                importSqliteDatatoExcel();
                            else
                                ActivityCompat.requestPermissions(Dashboard_Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE);
                        break;

                    case R.id.setting:
                        Intent intent=new Intent(Dashboard_Activity.this,Setting_Activity.class);
                        startActivityForResult(intent,REQUEST_SETTING);
                        break;

                    case R.id.rateApp:

                        break;

                    default:

                }

                return true;
            }
        });
    }

    private void importSqliteDatatoExcel() {

        ArrayList<String> columnsToExclude = new ArrayList<String>();
        columnsToExclude.add("id");
        SQLiteToExcel sqLiteToExcel = new SQLiteToExcel(Dashboard_Activity.this, AppDatabase.DATABASE_NAME, directory_path);
        sqLiteToExcel.setExcludeColumns(columnsToExclude);

        HashMap<String, String> prettyNameMapping = new HashMap<String, String>();
        prettyNameMapping.put("Full_Name", "Name");
        prettyNameMapping.put("CNIC_Issue_Date", "CNIC Issue Date");
        prettyNameMapping.put("Registration_Status", "Registered / UnRegistered");
        prettyNameMapping.put("Zilla", "District");
        sqLiteToExcel.setPrettyNameMapping(prettyNameMapping);
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        sqLiteToExcel.exportSingleTable("Register_People", "Ehsaas " + formattedDate + ".xlsx", new SQLiteToExcel.ExportListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(String filePath) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard_Activity.this);
                builder.setCancelable(false);
                builder.setMessage("Your Data is successfully exported to excel file");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        closeDrawer();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                Log.i("check", "Successfully Exported");
            }

            @Override
            public void onError(Exception e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard_Activity.this);
                builder.setCancelable(false);
                builder.setMessage("Failed to write data on excel file");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        closeDrawer();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                Log.i("check", "Export Failed " + e.getMessage());
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    importSqliteDatatoExcel();
                    closeDrawer();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard_Activity.this);
                    builder.setCancelable(false);
                    builder.setMessage("Unable to perform this operation without app permission.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            closeDrawer();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                break;
            default:
                return;
        }
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        } else {
            super.onBackPressed();
        }
    }

    private void closeDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FORM_REQUEST_CODE) {
            if (resultCode == Form_Activity.RESULT_CODE_DONE) {
                Toast.makeText(Dashboard_Activity.this, "Person is successfully registered", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Dashboard_Activity.this, "You did not register the person correctly.", Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
