package com.example.rickmortymvvm.data.local.page

import com.example.rickmortymvvm.data.local.models.PagesLocal

class PageDataLocalImpl(
    private val pagesLocalDao: PagesLocalDao
) : PageDataLocal {
    override fun savaPage(page: Int) {
        pagesLocalDao.savaPage(PagesLocal(page))
    }

    override fun getPage(): Int {
        return pagesLocalDao.getPage().page
    }
}
