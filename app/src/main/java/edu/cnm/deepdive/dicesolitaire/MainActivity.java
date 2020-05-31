package edu.cnm.deepdive.dicesolitaire;

import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.dicesolitaire.model.Roll;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

  /*defined two constants with format strings appropriate for the TextView and
ProgressBar items just below
 */
  private static final String SCRATCH_LABEL_ID_FORMAT = "scratch_%id_label";
  private static final String SCRATCH_COUNT_ID_FORMAT = "scratch_%id_count";
  //used Refactor/Rename to change the String name below
  private static final String PAIR_LABEL_ID_FORMAT = "pair_%d_label";
  //used Refactor/Rename to change the String name below
  private static final String PAIR_COUNT_ID_FORMAT = "pair_%d_count";
  private int minPairValue = 2;
  private int maxPairValue;
  //used Refactor/Rename to change the name below
  private TextView[] pairlabels;
  //used Refactor/Rename to change the name below
  private ProgressBar[] paircounts;
  private Button roller;
  private TextView rollDisplay;
  private Random rng;
  //defined the two fields below
  private TextView[] scratchLabels;
  private ProgressBar[] scratchCounts;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    maxPairValue = 2 * Roll.NUM_FACES;
    pairlabels = new TextView[maxPairValue - minPairValue + 1];
    paircounts = new ProgressBar[maxPairValue - minPairValue + 1];
    Resources res = getResources();
    rng = new Random();
    NumberFormat formatter = NumberFormat.getInstance();
    for (int i = minPairValue; i <= maxPairValue; i++) {
      String labelIdstring = String.format(PAIR_LABEL_ID_FORMAT, i);
      int labelId = res.getIdentifier(labelIdstring, "id", getOpPackageName());
      pairlabels[i - minPairValue] = findViewById(labelId);
      pairlabels[i - minPairValue].setText(formatter.format(i));
      String countIdString = String.format(PAIR_COUNT_ID_FORMAT, i);
      int countId = res.getIdentifier(countIdString, "id", getPackageName());
      paircounts[i - minPairValue] = findViewById(countId);
      paircounts[i - minPairValue].setProgress(1 + rng.nextInt(10));
      //declared scratchLabels array below
      scratchLabels = new TextView[maxPairValue - minPairValue + 1];
      //declared scratchCounts array below - no variable and no space allocated
      scratchCounts = new ProgressBar[maxPairValue - minPairValue + 1];
      //started a for loop
      for (int j = 1; j <= Roll.NUM_FACES; j++) {
        //constructed a string with the name of the id resource below
        String labelIdString2 = String.format(SCRATCH_LABEL_ID_FORMAT, j);
        //invoked the getIdentifier below
        int labelId2 = res.getIdentifier(labelIdString2, "id", getOpPackageName());
        //findViewById method below
        scratchLabels[0] = findViewById(labelId2);
        //formatter variable and setText method below
        scratchLabels[0].setText(formatter.format(j));
        //string for name of id resource for the current ProgressBar element
        String countIdString2 = String.format(SCRATCH_COUNT_ID_FORMAT, j);
        //invoke the getIdentifier method on the res local variable below
        int countId2 = res.getIdentifier(countIdString, "id", getPackageName());
        //findViewById method below
        scratchCounts[0] = findViewById(labelId2);
        //setProgress method below
        scratchCounts[0].setProgress(1 + rng.nextInt(7));

        
      }
    }
    roller = findViewById(R.id.roller);
    rollDisplay = findViewById(R.id.roll_display);
    roller.setOnClickListener(new RollerListener());
  }

  private class RollerListener implements OnClickListener {

    @Override
    public void onClick(View v) {
      Roll roll = new Roll(rng);
      rollDisplay.setText(Arrays.toString(roll.getDice()));
    }
  }
}
