package ovh.major.songifyclient.client.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ovh.major.songifyclient.client.SongifyProxy;
import ovh.major.songifyclient.client.dto.*;

@Controller
@Log4j2
public class SongsViewController {

    private SongsDto database;
    private SongifyProxy songifyProxy;
    private String ex;

    @Autowired
    public SongsViewController(SongifyProxy songifyProxy) {
        this.songifyProxy = songifyProxy;
    }

    @GetMapping("/")
    public String home(Model model) {
        this.database = songifyProxy.getAllSongs();
        model.addAttribute("songMap", database);
        return "viewSongs.html";
    }

    @GetMapping("/e")
    public String e(Model model) {
        this.database = songifyProxy.getAllSongs();
        model.addAttribute("songMap", database);
        model.addAttribute("e", ex);
        ex = null;
        return "viewSongs.html";
    }

    @GetMapping("/songs")
    public String viewAllSongsAndChosedSong(@RequestParam Integer id, Model model) {
        SingleSongResponseDto song;
        try {
            song = songifyProxy.getSongById(id);
        } catch (Exception exception) {
            log.warn("Bład klienta: " + exception);
            ex = exception.toString();
            return "redirect:/e";
        }
        model.addAttribute("songMap", database);
        model.addAttribute("song", song);
        return "viewSongs.html";
    }

    @PostMapping("/songs")
    public String addSong(@RequestParam String songName, @RequestParam String artist) {
        SingleSongRequestDto singleSongRequestDto = SingleSongRequestDto.builder()
                .songName(songName)
                .artist(artist)
                .build();
        songifyProxy.postSong(singleSongRequestDto);
        log.info("wysłano rządanie dodania nowej piosenki");
        return "redirect:/";
    }

    @PostMapping("/deletesong")
    public String deleteSong(@RequestParam Integer id) {
        try {
            songifyProxy.deleteSong(id);
        } catch (Exception exception) {
            log.warn("Bład klienta: " + exception);
            ex = exception.toString();
            return "redirect:/e";
        }
        log.info("wysłano rządanie usunięcia nowej piosenki");
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String editSong(@RequestParam Integer id, @RequestParam String songName, @RequestParam String artist) {

        if (!songName.isBlank() && !artist.isBlank()) {
            SingleSongRequestDto request = SingleSongRequestDto.builder()
                    .songName(songName)
                    .artist(artist)
                    .build();
            try {
                songifyProxy.putSong(id, request);
                return "redirect:/";
            } catch (Exception exception) {
                log.warn("Bład klienta: " + exception);
                ex = exception.toString();
                return "redirect:/e";
            }
        }
        if (!artist.isBlank()) {
            try {
                ArtistRequestDto request = ArtistRequestDto.builder()
                        .artist(artist)
                        .build();
                songifyProxy.patchArtist(id, request);
                return "redirect:/";
            } catch (Exception exception) {
                log.warn("Bład klienta: " + exception);
                ex = exception.toString();
                return "redirect:/e";
            }
        }

        if (!songName.isBlank()) {
            try {
                SongNameRequestDto request = SongNameRequestDto.builder()
                        .songName(songName)
                        .build();
                songifyProxy.patchSongName(id, request);
                return "redirect:/";
            } catch (Exception exception) {
                log.warn("Bład klienta: " + exception);
                ex = exception.toString();
                return "redirect:/e";
            }
        }

        log.warn("Bład klienta: " + "klient nie podał danych do aktualizacji.");
        ex = " ERROR: aby edytować piosenkę, musisz cokolwiek wpisać w polę artist lub songName";
        return "redirect:/e";
    }

}
