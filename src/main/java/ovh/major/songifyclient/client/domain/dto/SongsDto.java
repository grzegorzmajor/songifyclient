package ovh.major.songifyclient.client.domain.dto;

import java.util.Map;

public record SongsDto(Map<Integer,SingleSongResponseDto> songs) {
}
