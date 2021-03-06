/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.architecture.blueprints.todoapp.statistics

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.architecture.blueprints.todoapp.R
import kotlinx.android.synthetic.main.statistics_frag.statistics as mStatisticsTV

/**
 * Main UI for the statistics screen.
 */
class StatisticsFragment : Fragment(), StatisticsContract.View {

    lateinit var mPresenter: StatisticsContract.Presenter

    override fun setPresenter(presenter: StatisticsContract.Presenter) {
        mPresenter = presenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.statistics_frag, container, false)

    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }

    override fun setProgressIndicator(active: Boolean) {
        mStatisticsTV.text = if (active) getString(R.string.loading) else ""
    }

    override fun showStatistics(numberOfIncompleteTasks: Int, numberOfCompletedTasks: Int) {
        if (numberOfCompletedTasks == 0 && numberOfIncompleteTasks == 0) {
            mStatisticsTV.text = resources.getString(R.string.statistics_no_tasks)
        } else {
            val displayString = "${resources.getString(R.string.statistics_active_tasks)} " +
                    "$numberOfIncompleteTasks\n${resources.getString(R.string.statistics_completed_tasks)} " +
                    "$numberOfCompletedTasks"
            mStatisticsTV.text = displayString
        }
    }

    override fun showLoadingStatisticsError() {
        mStatisticsTV.text = resources.getString(R.string.statistics_error)
    }

    override fun isActive() = isAdded

    companion object {
        fun newInstance() = StatisticsFragment()
    }
}
