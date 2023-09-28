package ovh.major.songifyclient.client.dto;

import lombok.Builder;

@Builder
public record SingleSongResponseDto(
        String songName,
        String artist) {
}

