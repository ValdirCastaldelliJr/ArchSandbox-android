package com.castaldelli.archsandbox.view

import android.content.Intent
import com.castaldelli.archsandbox.core.CoreActivity
import com.castaldelli.archsandbox.view.activity.MenuActivity
import com.castaldelli.archsandbox.view.activity.TodoListActivity

object ViewRouter {

    fun goToMenuActivity(ctx : CoreActivity) {
        ctx.startActivity(Intent(ctx, MenuActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        })
        ctx.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
    fun goTodoList(ctx : CoreActivity) {
        ctx.startActivity(Intent(ctx, TodoListActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        })
        ctx.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

}