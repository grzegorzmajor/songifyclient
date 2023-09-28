package ovh.major.songifyclient.client.dto;

import lombok.Builder;

@Builder
public record SingleSongRequestDto(
        String songName,
        String artist

) {
}
