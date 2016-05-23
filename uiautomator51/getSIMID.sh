echo SIM1 is:
adb shell sqlite3 /data/data/com.android.providers.telephony/databases/telephony.db "select icc_id from siminfo where sim_id=0"
echo SIM2 is:
adb shell sqlite3 /data/data/com.android.providers.telephony/databases/telephony.db "select icc_id from siminfo where sim_id=1"

