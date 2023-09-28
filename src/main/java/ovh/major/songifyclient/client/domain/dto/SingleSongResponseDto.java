package ovh.major.songifyclient.client.domain.dto;

import lombok.Builder;

@Builder
public record SingleSongResponseDto(
        String songName,
        String artist) {
}

