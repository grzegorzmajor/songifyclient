package ovh.major.songifyclient.client.domain;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ovh.major.songifyclient.client.domain.dto.*;

@Component
@FeignClient(value = "songify-client", url = "${app.url}")
interface SongifyProxy {

    @GetMapping("/songs")
    SongsDto getAllSongs();

    @GetMapping("/songs")
    SongsDto getAllSongsFragmented(
            @RequestParam("limit") String limit
    );

    @GetMapping("/songs/{id}")
    SingleSongResponseDto getSongById(
            @PathVariable Integer id
    );

    @PostMapping("/songs")
    SingleSongResponseDto postSong(@RequestBody SingleSongRequestDto song);

    @DeleteMapping("/songs/{id}")
    void deleteSong(@PathVariable Integer id);

    @PatchMapping("/songs/{id}")
    void patchSongName(@PathVariable Integer id, @RequestBody SongNameRequestDto request);
    @PatchMapping("/songs/{id}")
    void patchArtist(@PathVariable Integer id, @RequestBody ArtistRequestDto request);

    @PutMapping("/songs/{id}")
    void putSong(@PathVariable Integer id, @RequestBody SingleSongRequestDto request);
}
