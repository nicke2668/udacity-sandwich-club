package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

	public static Sandwich parseSandwichJson(String rawJson) {
		String mainName = "";
		List<String> alsoKnownAs = new ArrayList<>();
		String placeOfOrigin = "";
		String description = "";
		String image = "";
		List<String> ingredients = new ArrayList<>();
		Sandwich sandwich = new Sandwich();

		try {

			JSONObject object = new JSONObject(rawJson);
			JSONObject nameObject = object.getJSONObject("name");
			mainName = nameObject.getString("mainName");
			JSONArray array = nameObject.getJSONArray("alsoKnownAs");
			for (int i = 0; i < array.length(); i++) {
				{
					alsoKnownAs.add(array.getString(i));

				}
			}

			array = object.getJSONArray("ingredients");
			for (int i = 0; i < array.length(); i++) {
				{
					ingredients.add(array.getString(i));

				}
			}

			description = object.getString("description");
			image = object.getString("image");
			placeOfOrigin = object.getString("placeOfOrigin");

		} catch (JSONException exception) {
			Log.e(JsonUtils.class.getSimpleName(), exception.getLocalizedMessage());
		}

		sandwich.setAlsoKnownAs(alsoKnownAs);
		sandwich.setDescription(description);
		sandwich.setImage(image);
		sandwich.setIngredients(ingredients);
		sandwich.setMainName(mainName);
		sandwich.setPlaceOfOrigin(placeOfOrigin);

		return sandwich;
	}
}
