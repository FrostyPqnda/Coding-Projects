import base64

open_klIcon = open('Logo.ico','rb')
klB64str = base64.b64encode(open_klIcon.read())
open_klIcon.close()
write_data = ('iconImg = %s' % klB64str)
f = open('icon.py','w+')
f.write(write_data)
f.close()

open_bpLogo = open('BP.ico', 'rb')
bpB64str = base64.b64encode(open_bpLogo.read())
open_bpLogo.close()
write_data = ('logoImg = %s' % bpB64str)
f = open('logo.py', 'w+')
f.write(write_data)
f.close()