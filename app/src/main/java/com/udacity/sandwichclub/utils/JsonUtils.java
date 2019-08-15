package com.udacity.sandwichclub.utils;

import android.util.JsonReader;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import java.io.IOException;
import java.io.StringReader;
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
		JsonReader reader = new JsonReader(new StringReader(rawJson));
		Sandwich sandwich = new Sandwich();

		try {
			reader.beginObject();
			while (reader.hasNext()) {
				String name = reader.nextName();
				switch (name) {
					case "name":
						reader.beginObject();
						while (reader.hasNext()) {
							String objectName = reader.nextName();
							switch (objectName) {
								case "mainName":
									mainName = reader.nextString();
									break;
								case "alsoKnownAs":
									reader.beginArray();
									while (reader.hasNext()) {
										alsoKnownAs.add(reader.nextString());
									}
									reader.endArray();
									break;
							}
						}
						reader.endObject();
						break;
					case "alsoKnownAs":
						reader.beginArray();
						while (reader.hasNext()) {
							alsoKnownAs.add(reader.nextString());
						}
						reader.endArray();
						break;
					case "placeOfOrigin":
						placeOfOrigin = reader.nextString();
						break;
					case "description":
						description = reader.nextString();
						break;
					case "image":
						image = reader.nextString();
					case "ingredients":
						reader.nextName();
						reader.beginArray();
						while (reader.hasNext()) {
							ingredients.add(reader.nextString());
						}
						reader.endArray();
						break;
					default:
						reader.skipValue();
				}

			}
			reader.endObject();

		} catch (IOException exception) {
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
