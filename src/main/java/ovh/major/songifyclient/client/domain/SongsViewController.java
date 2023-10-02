package ovh.major.songifyclient.client.domain;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ovh.major.songifyclient.client.domain.dto.*;

@Controller
@Log4j2
class SongsViewController {

    private SongsDto database;
    private SongifyProxy songifyProxy;
    private String error;

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

    @GetMapping("/error")
    public String e(Model model) {
        this.database = songifyProxy.getAllSongs();
        model.addAttribute("songMap", database);
        model.addAttribute("error", error);
        error = null;
        return "viewSongs.html";
    }

    @GetMapping("/songs")
    public String viewAllSongsAndChosedSong(@RequestParam Integer id, Model model) {
        SingleSongResponseDto song;
        try {
            song = songifyProxy.getSongById(id);
        } catch (Exception exception) {
            log.warn("ERROR: " + exception);
            //ex = exception.toString();
            return "redirect:/error";
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
        log.info("a request to add a new song has been sent");
        return "redirect:/";
    }

    @PostMapping("/deletesong")
    public String deleteSong(@RequestParam Integer id) {
        try {
            songifyProxy.deleteSong(id);
        } catch (Exception exception) {
            log.warn("ERROR: " + exception);
            //ex = exception.toString();
            return "redirect:/e";
        }
        log.info("a removal request has been submitted");
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
                log.warn("ERROR: " + exception);
                //ex = exception.toString();
                return "redirect:/error";
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
                log.warn("ERROR: " + exception);
                //ex = exception.toString();
                return "redirect:/error";
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
                log.warn("ERROR: " + exception);
                //ex = exception.toString();
                return "redirect:/error";
            }
        }

        log.warn("ERROR: " + "no data to update");
        error = " ERROR: To edit a song, you must enter anything in the artist or songName field";
        return "redirect:/error";
    }

}
