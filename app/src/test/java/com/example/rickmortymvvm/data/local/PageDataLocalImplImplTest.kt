package com.example.rickmortymvvm.data.local

import com.example.rickmortymvvm.data.local.models.PagesLocal
import com.example.rickmortymvvm.data.local.page.PageDataLocalImpl
import com.example.rickmortymvvm.data.local.page.PagesLocalDao
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PageDataLocalImplImplTest {

    lateinit var pagesLocalDaoMock: PagesLocalDao
    lateinit var pagesDataLocal: PageDataLocalImpl

    @Before
    fun before() {
        pagesLocalDaoMock = mockk()
        pagesDataLocal = PageDataLocalImpl(pagesLocalDaoMock)
    }

    @Test
    fun testSavaPages() {
        val pagesTest = PagesLocal(2)
        every { pagesLocalDaoMock.savaPage(pagesTest) } returns Unit

        pagesDataLocal.savaPage(2)

        verify(exactly = 1) { pagesLocalDaoMock.savaPage(any()) }
    }

    @Test
    fun testGetPages() {
        every { pagesLocalDaoMock.getPage() } returns PagesLocal(2)

        val result = pagesDataLocal.getPage()

        assertEquals(2, result)
    }
}
