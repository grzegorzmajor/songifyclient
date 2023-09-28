package ovh.major.songifyclient.client.domain.dto;

import lombok.Builder;

@Builder
public record SingleSongRequestDto(
        String songName,
        String artist

) {
}
