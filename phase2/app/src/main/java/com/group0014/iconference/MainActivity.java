//package com.group0014.iconference;
//
//import android.os.Bundle;
//import android.widget.Toast;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class MainActivity extends AppCompatActivity {
//
//  FirebaseUser firebaseUser;
//  DatabaseReference databaseReference;
//
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_main);
//
//    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//    databaseReference = FirebaseDatabase.getInstance().getReference("Users")
//        .child(firebaseUser.getUid());
//
//    databaseReference.addValueEventListener(new ValueEventListener() {
//      @Override
//      public void onDataChange(@NonNull DataSnapshot snapshot) {
//        User user = snapshot.getValue(User.class);
//        System.out.println(user.getType());
//      }
//
//      @Override
//      public void onCancelled(@NonNull DatabaseError error) {
//        Toast
//            .makeText(MainActivity.this, "Can't find user type!", Toast.LENGTH_SHORT)
//            .show();
//      }
//    });
//  }
//}