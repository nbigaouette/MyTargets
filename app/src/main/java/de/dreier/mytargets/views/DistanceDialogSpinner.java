/*
 * MyTargets Archery
 *
 * Copyright (C) 2015 Florian Dreier
 * All rights reserved
 */

package de.dreier.mytargets.views;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.widget.TextView;

import de.dreier.mytargets.R;
import de.dreier.mytargets.activities.ItemSelectActivity;
import de.dreier.mytargets.shared.models.Distance;

import static de.dreier.mytargets.activities.ItemSelectActivity.ITEM;

public class DistanceDialogSpinner extends DialogSpinner<Distance> {

    public DistanceDialogSpinner(Context context) {
        this(context, null);
    }

    public DistanceDialogSpinner(Context context, AttributeSet attrs) {
        super(context, attrs, R.layout.item_distance);
        setOnClickListener(v -> {
            Intent i = new Intent(getContext(), ItemSelectActivity.Distance.class);
            i.putExtra(ITEM, item);
            startIntent(i, data -> setItem((Distance) data.getSerializableExtra(ITEM)));
        });
    }

    @Override
    protected void bindView() {
        TextView name = (TextView) mView.findViewById(android.R.id.text1);
        name.setText(item.toString(getContext()));
    }
}
