package ru.geekbrains.materialdesign.view.animations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import ru.geekbrains.materialdesign.R
import ru.geekbrains.materialdesign.databinding.FragmentConstraintSetBinding

class ConstraintSetFragment : Fragment() {

    private var _binding: FragmentConstraintSetBinding? = null
    private val binding: FragmentConstraintSetBinding get() { return _binding!!}
    private var flag = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConstraintSetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.root)

        binding.backgroundImage.setOnClickListener {
            flag = !flag
            val changeBounds = ChangeBounds().apply {
                duration = 1000L
                interpolator = AnticipateOvershootInterpolator(5.0f)
            }
            TransitionManager.beginDelayedTransition(binding.root, changeBounds)
            if (flag) {
                constraintSet.apply {
                    connect(R.id.title, ConstraintSet.END, R.id.constraint_container, ConstraintSet.END)
                    clear(R.id.date, ConstraintSet.BOTTOM)
                    connect(R.id.date, ConstraintSet.TOP, R.id.title, ConstraintSet.BOTTOM)
                    clear(R.id.date, ConstraintSet.START)
                    connect(R.id.date, ConstraintSet.END, R.id.title, ConstraintSet.END)
                    clear(R.id.description, ConstraintSet.TOP)
                    connect(R.id.description, ConstraintSet.BOTTOM, R.id.constraint_container, ConstraintSet.BOTTOM)
                    applyTo(binding.root)
                }
            } else {
                constraintSet.apply {
                    connect(R.id.title, ConstraintSet.END, R.id.constraint_container, ConstraintSet.START)
                    clear(R.id.date, ConstraintSet.BOTTOM)
                    connect(R.id.date, ConstraintSet.TOP, R.id.title, ConstraintSet.BOTTOM)
                    clear(R.id.date, ConstraintSet.START)
                    connect(R.id.date, ConstraintSet.END, R.id.title, ConstraintSet.END)
                    clear(R.id.description, ConstraintSet.BOTTOM)
                    connect(R.id.description, ConstraintSet.TOP, R.id.constraint_container, ConstraintSet.BOTTOM)
                    applyTo(binding.root)
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ConstraintSetFragment()
    }
}