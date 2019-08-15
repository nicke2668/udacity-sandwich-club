package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

	public static final String EXTRA_POSITION = "extra_position";
	private static final int DEFAULT_POSITION = -1;
	private int position;
	private Sandwich sandwich;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		considerClosingOnError(getIntent());

		String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
		String json = sandwiches[position];
		sandwich = JsonUtils.parseSandwichJson(json);
		if (sandwich == null) {
			// Sandwich data unavailable
			closeOnError();
			return;
		}

		populateUI();


		setTitle(sandwich.getMainName());
	}

	private void considerClosingOnError(Intent intent) {
		if (intent == null || isDefaultPosition(intent)) {
			// EXTRA_POSITION not found in intent
			closeOnError();
		}
	}

	private boolean isDefaultPosition(Intent intent) {
		position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
		return position == DEFAULT_POSITION;
	}

	private void closeOnError() {
		finish();
		Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
	}

	private void populateUI() {

		TextView origin = findViewById(R.id.origin_tv);
		TextView alsoKnownAs = findViewById(R.id.also_known_tv);
		TextView description = findViewById(R.id.description_tv);
		TextView ingredients = findViewById(R.id.ingredients_tv);
		description.setText(sandwich.getDescription());
		origin.setText(sandwich.getPlaceOfOrigin());
		StringBuilder builder = new StringBuilder();
		for (String ingredient : sandwich.getIngredients()) {
			builder.append(ingredient).append("\n");
		}

		ingredients.setText(builder.toString());
		builder = new StringBuilder();

		for (String alias : sandwich.getAlsoKnownAs()) {
			builder.append(alias).append("\n");
		}

		alsoKnownAs.setText(builder.toString());

		ImageView sandwichPicture = findViewById(R.id.image_iv);
		Picasso.with(this)
				.load(sandwich.getImage())
				.into(sandwichPicture);


	}
}
