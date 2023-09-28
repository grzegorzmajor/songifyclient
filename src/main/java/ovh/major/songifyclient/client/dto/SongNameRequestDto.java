package ovh.major.songifyclient.client.dto;

import lombok.Builder;

@Builder
public record SongNameRequestDto(
        String songName)
{

}
