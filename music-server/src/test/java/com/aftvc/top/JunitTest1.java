package com.aftvc.top;/**
 * Created by MtRider on 2020/10/20 21:20
 */

import com.aftvc.top.domain.ListSong;
import com.aftvc.top.service.ListSongService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * @ Author     ：Yan
 * @ Date       ：Created in 21:20 2020/10/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class JunitTest1 {

    @Autowired
    private ListSongService listSongService;

    @Test
    public void testListSong(){
        List<ListSong> list = listSongService.list();
        for (ListSong song : list) {
            System.out.println(song);
        }
    }
}
