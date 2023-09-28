package ovh.major.songifyclient.client.dto;

import lombok.Builder;

@Builder
public record ArtistRequestDto(
        String artist)
{

}
