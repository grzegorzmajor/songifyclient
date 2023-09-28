package ovh.major.songifyclient.client.dto;

import java.util.Map;

public record SongsDto(Map<Integer,SingleSongResponseDto> songs) {
}
