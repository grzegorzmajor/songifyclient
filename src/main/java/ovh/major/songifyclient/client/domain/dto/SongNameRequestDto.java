package ovh.major.songifyclient.client.domain.dto;

import lombok.Builder;

@Builder
public record SongNameRequestDto(
        String songName)
{

}
