/*
 * Copyright (C) 2014 ohmage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ohmage.prompts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ohmage.app.R;
import org.ohmage.widget.NumberPicker;
import org.ohmage.widget.NumberPicker.OnChangedListener;

import java.math.BigDecimal;

/**
 * Created by cketcham on 1/24/14.
 * TODO:can this be combined with textprompt? because the json is the same
 */
public class NumberPrompt extends AnswerablePrompt {
    public BigDecimal min;
    public BigDecimal max;
    public boolean wholeNumbersOnly;

    @Override
    public Fragment getFragment() {
        return NumberPromptFragment.getInstance(this);
    }

    public static class NumberPromptFragment extends AnswerablePromptFragment {

        public static NumberPromptFragment getInstance(NumberPrompt prompt) {
            NumberPromptFragment fragment = new NumberPromptFragment();
            fragment.setPrompt(prompt);
            return fragment;
        }

        @Override
        public void onCreatePromptView(LayoutInflater inflater, final ViewGroup container,
                Bundle savedInstanceState) {
            ViewGroup view = (ViewGroup) inflater.inflate(R.layout.prompt_number, container, true);

            ((TextView) view.findViewById(R.id.text)).setText(getPrompt().getText());
            NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.number_picker);
            numberPicker.setRange(((NumberPrompt) getPrompt()).min,
                    ((NumberPrompt) getPrompt()).max);
            numberPicker.setOnChangeListener(new OnChangedListener() {
                @Override
                public void onChanged(NumberPicker picker, BigDecimal oldVal, BigDecimal newVal) {
                    setValue(newVal);
                }
            });
        }
    }
}