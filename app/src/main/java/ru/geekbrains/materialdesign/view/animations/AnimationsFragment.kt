package ru.geekbrains.materialdesign.view.animations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import ru.geekbrains.materialdesign.databinding.FragmentAnimationsBinding


class AnimationsFragment : Fragment() {

    private var _binding: FragmentAnimationsBinding? = null
    private val binding: FragmentAnimationsBinding get() { return _binding!! }
    private var flag = false
    private var duration = 1000L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.fab.setOnClickListener {

            flag = !flag

            if (flag) {
                ObjectAnimator
                    .ofFloat(binding.fab, View.ROTATION, 315f)
                    .setDuration(duration)
                    .start()
                ObjectAnimator
                    .ofFloat(binding.plusImageview, View.ROTATION, 315f)
                    .setDuration(duration)
                    .start()
                ObjectAnimator
                    .ofFloat(binding.optionOneContainer, View.TRANSLATION_Y, -250f)
                    .setDuration(duration)
                    .start()
                ObjectAnimator
                    .ofFloat(binding.optionTwoContainer, View.TRANSLATION_Y, -130f)
                    .setDuration(duration)
                    .start()

                binding.transparentBackground.animate()
                    .alpha(0.5f)
                    .setDuration(duration)
                    .start()

                binding.optionOneContainer.animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.optionOneContainer.isClickable = true
                        }
                    })

                binding.optionTwoContainer.animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.optionOneContainer.isClickable = true
                        }
                    })

            } else {
                ObjectAnimator
                    .ofFloat(binding.fab, View.ROTATION, 0f)
                    .setDuration(duration)
                    .start()
                ObjectAnimator
                    .ofFloat(binding.plusImageview, View.ROTATION, 0f)
                    .setDuration(duration)
                    .start()
                ObjectAnimator
                    .ofFloat(binding.optionOneContainer, View.TRANSLATION_Y, 0f)
                    .setDuration(duration)
                    .start()
                ObjectAnimator
                    .ofFloat(binding.optionTwoContainer, View.TRANSLATION_Y, 0f)
                    .setDuration(duration)
                    .start()

                binding.transparentBackground.animate()
                    .alpha(0f)
                    .setDuration(duration)
                    .start()

                binding.optionOneContainer.animate()
                    .alpha(0f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationStart(animation: Animator?) {
                            binding.optionOneContainer.isClickable = false
                        }
                    })

                binding.optionTwoContainer.animate()
                    .alpha(0f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationStart(animation: Animator?) {
                            binding.optionOneContainer.isClickable = false
                        }
                    })
            }
        }
        binding.scrollView.setOnScrollChangeListener { _, _, _, _, _ ->
            binding.header.isSelected = binding.scrollView.canScrollVertically(-1)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() : Fragment {
            return AnimationsFragment()
        }
    }

}