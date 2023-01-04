package info.palomatica.puzlecolores;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
/**
 * Created by root on 26/08/17.
 */

@SuppressWarnings("deprecation")
public class PoolPlayer
{
    private static Context _context;

    private static SoundPool _soundPool;

    // This is the identifier of the loaded sound


    public static void initializePlayer(Context context)
    {
        if(_context == null || _soundPool == null)
        {
            _context = context;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                _soundPool = new SoundPool.Builder()
                        .setMaxStreams(2)
                        .build();
            }
            else
            {
                _soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 1);
            }

        }
        _soundPool.load(_context, R.raw.shiny, 1); // 1
        _soundPool.load(_context, R.raw.record, 1); // 2
    }



    public static void playSound(int idSound)
    {
        int id = -1;
        switch (idSound)
        {
            case R.raw.shiny:
                id = 1;
                break;
            case R.raw.record:
                id = 2;
                break;
        }
        _soundPool.play(id, 0.5f, 0.5f, 1, 0,1);
    }

}
